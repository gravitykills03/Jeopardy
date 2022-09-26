# jeopardy
Project based on the late and great Alex Trebek.

## Git Workflow

1) Create a branch 
  - Open git tab on bottom-left on IntelliJ.  Navigate to "log" tab.
  - Find the 'main' branch, right-click and create branch giving it a name
2) Work on branch
  - Proceed as normal working on whatever task you are doing in your branch.
  - The branch should automatically be your tagged branch if the above steps were followed
3) Commit commit commit..
  - Commit as often as you want.  Commits are local to your machine only, essentially a save button
4) Ready to push?
  - When you feel you are ready to push your work to GitHub you MUST update your main branch to be safe.
    - Right-click on main in git tab and select update.
  - Push code to GitHub.
5) Open a PR
  - On github.com navigate to your branch and open a PR on your own branch. We each will own this step.
  - Notify your teammates that there is an open PR. ALL team members should review the code to check for errors. The more eyes we have on each other's code the more we can catch slip-ups 
  - When approved by teammates, the owner of the branch / pr merges to main.
6) Branch Merged with main
  - Delete the branch after the merge screen on github.com
  - Notify the team that a merge has been completed. ALL teammembers will update their main branch in IntelliJ (refer to step 4)
  - In IntelliJ, delete the branch you just merged.
