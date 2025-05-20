import os
import shutil
import subprocess
import itertools
import platform
from collections import defaultdict

PROJECT_DIR = os.path.join(os.path.dirname(__file__), "HorrorFX")
MODS_DIR = os.path.join(PROJECT_DIR, "mods-mvn")
BACKUP_DIR = os.path.join(PROJECT_DIR, "mods-mvn-backup")

JAVA = "java"
MAIN_MODULE = "Core"
MAIN_CLASS = "dk.sdu.smp4.main.Main"

REQUIRED_JARS = {
    "javafx-base-21.0.2-win.jar",
    "javafx-base-21.0.2.jar",
    "javafx-controls-21.0.2-win.jar",
    "javafx-controls-21.0.2.jar",
    "javafx-graphics-21.0.2-win.jar",
    "javafx-graphics-21.0.2.jar",
    "Common-1.0-SNAPSHOT.jar",
    "CommonGUIElements-1.0-SNAPSHOT.jar",
    "Core-1.0-SNAPSHOT.jar",
    "CommonInteractable-1.0-SNAPSHOT.jar",
    "CommonLightSource-1.0-SNAPSHOT.jar",
    "CommonMap-1.0-SNAPSHOT.jar",
    "CommonPlayer-1.0-SNAPSHOT.jar",
    "CommonPlayerLight-1.0-SNAPSHOT.jar",
    "CommonQuest-1.0-SNAPSHOT.jar",
    "CommonEnemy-1.0-SNAPSHOT.jar",
    "CommonEvents-1.0-SNAPSHOT.jar"
}

MAX_COMBINATION_SIZE = 4

# Step 0: Build project
print("Running `mvn clean install` to ensure latest JARs are built...")
build_result = subprocess.run(
    ["mvn.cmd" if platform.system() == "Windows" else "mvn", "clean", "install"],
    cwd=PROJECT_DIR,
    capture_output=True,
    text=True
)
if build_result.returncode != 0:
    print("Build failed. Please fix errors before testing.")
    print(build_result.stderr)
    exit(1)
print("Build complete.\n")

# Step 1: Backup all JARs
os.makedirs(BACKUP_DIR, exist_ok=True)
for f in os.listdir(BACKUP_DIR):
    path = os.path.join(BACKUP_DIR, f)
    if os.path.isfile(path):
        os.remove(path)
    elif os.path.isdir(path):
        shutil.rmtree(path)

for jar in os.listdir(MODS_DIR):
    if jar.endswith(".jar"):
        shutil.copy(os.path.join(MODS_DIR, jar), BACKUP_DIR)

all_jars = sorted(j for j in os.listdir(BACKUP_DIR) if j.endswith(".jar"))
missing_required = REQUIRED_JARS - set(all_jars)
if missing_required:
    raise ValueError(f"Required jars not found: {missing_required}")

# Step 2: Build test configurations
optional_jars = [j for j in all_jars if j not in REQUIRED_JARS]
configs = []

for i in range(0, min(MAX_COMBINATION_SIZE, len(optional_jars)) + 1):
    for optional_subset in itertools.combinations(optional_jars, i):
        test_set = REQUIRED_JARS.union(optional_subset)
        missing_set = set(optional_jars) - set(optional_subset)
        configs.append((test_set, missing_set))

print(f"Generated {len(configs)} configurations to test...\n")


import time

def safe_remove(path, retries=5):
    for _ in range(retries):
        try:
            os.remove(path)
            return
        except PermissionError:
            time.sleep(0.2)
    raise PermissionError(f"Could not delete {path} after {retries} retries")


# Step 3: Define dry-run test
def run_test(config_index, jar_set, missing_jars):
    try:
        # Clear and copy test jars
        for f in os.listdir(MODS_DIR):
            safe_remove(os.path.join(MODS_DIR, f))
        for jar in jar_set:
            shutil.copy(os.path.join(BACKUP_DIR, jar), MODS_DIR)
    except Exception as e:
        return (config_index, jar_set, missing_jars, "ERROR", str(e))

    # Run dry-run command
    cmd = [
        JAVA,
        "--dry-run",
        "--module-path", MODS_DIR,
        "--module", f"{MAIN_MODULE}/{MAIN_CLASS}"
    ]

    result = subprocess.run(cmd, cwd=PROJECT_DIR, capture_output=True, text=True)
    status = "PASS" if result.returncode == 0 else "FAIL"

    return (config_index, jar_set, missing_jars, status, result.stderr.strip())

# Step 4: Run tests sequentially
results = []

for i, (jar_set, missing_jars) in enumerate(configs, start=1):
    config_index, jar_set, missing_jars, status, stderr = run_test(i, jar_set, missing_jars)
    results.append((config_index, jar_set, missing_jars, status, stderr))

    optional_included = sorted(j for j in jar_set if j not in REQUIRED_JARS)
    passed = (status == "PASS")
    #print(f"All jars for config {config_index}: {', '.join(optional_included)} Passed: {passed}")

# Step 5: Restore mods-mvn
for f in os.listdir(MODS_DIR):
    os.remove(os.path.join(MODS_DIR, f))
for jar in all_jars:
    shutil.copy(os.path.join(BACKUP_DIR, jar), MODS_DIR)
shutil.rmtree(BACKUP_DIR, ignore_errors=True)

# Step 6: Save results
output_file = os.path.join(os.path.dirname(__file__), "mod_test_results.txt")
with open(output_file, "w") as f:
    for config_index, jar_set, missing_jars, status, stderr in results:
        optional_included = sorted(j for j in jar_set if j not in REQUIRED_JARS)
        passed = (status == "PASS")
        f.write(f"All jars for config {config_index}: {', '.join(optional_included)} Passed: {passed}\n")
        if not passed:
            f.write("Error:\n")
            f.write(stderr + "\n\n")

# Step 7: Summarize failures
failure_summary = defaultdict(int)
for _, _, missing_jars, status, _ in results:
    if status != "PASS":
        for jar in missing_jars:
            failure_summary[jar] += 1

print("\n==== SUMMARY (Grouped Failures) ====")
if not failure_summary:
    print("✅ No failures detected. All combinations passed.")
else:
    for jar, count in sorted(failure_summary.items(), key=lambda x: -x[1]):
        print(f"{jar}: caused {count} failure(s)")

print(f"\n📄 Full test results saved to: {output_file}")
