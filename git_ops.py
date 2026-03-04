import subprocess
import sys, os

os.chdir(r"c:\Users\jeann\OneDrive\GIT FOLDER")

def run(cmd):
    print("$", cmd)
    try:
        out = subprocess.check_output(cmd, stderr=subprocess.STDOUT, shell=True, text=True)
        print(out)
    except subprocess.CalledProcessError as e:
        print(e.output)

if __name__ == "__main__":
    run('git status')
    run('git branch')
    run('git log --oneline -5')
    run('git pull origin main')
    run('git add .')
    run('git commit -m "Resolve conflicts and add DataRecord"')
    run('git push origin main')
