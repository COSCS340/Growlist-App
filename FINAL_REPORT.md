# Growlist-App -- Steven Engel, Alex Wilkinson, Brandon Yen, Casey Lemon

Introduction
=====
### Project Intro
Create an app that allows the user to add, view, update, and share their collection of plants.

### Motivation
A group member has a hobby of collecting plants and realized there were no apps available that would make this process easier, beside the bloated use of Excel.

### Our Approach
1. Created displaying of single plants

	We created a UI to just display a single plant entry and basic information about it. This was used as a UI test to see what we wanted the app to look like.

2. Created list viewing of plants

	We then enabled viewing of multiple plant entries for the user.

3. Rudimentary data storage options

	Since the core of the app is supposed to store all of the user's plant entries, we needed to create a basic version of a storage system. This was done using Android's Room database (more about in Technology section).

### Interruptions
Some features we were not able to get to due to time constraints, the most notable one would be a cloud sharing feature.

Customer Value
==============
No major changes were made from the original project proposal.

Technology
==========
### Architecture
Since our main focus is building an Android application, we are utilizing Android's SDK to build a majority of our app. The main components of our app, like the GUI, the storage, and the user experience, all use various parts of the SDK. For example, for storing our information we originally were looking to use a MySQL or LiteSQL. However, Android has it's own database interface called Room, which although is basically a wrapper for LiteSQL, it makes storing user data much easier.

### Iteration's Goals
Our goal for this iteration was to simply have a UI and be able to display multiple plants and add plants to the display. With the screenshot below, you can see we have a simple layout with the option of being able to add another plant to display information about.

INSERT SCREENSHOT HERE

### Tests Ran
While a majority of our team do not have a physical android environment to run our app on, we all would regularly test our app on the Android Emulator built into Android Studio. Running tests on the emulator allowed for quick feedback on various changes to the UI or app functionality. When we had a more stable or more updated version of our app ready, the group member with an Android device would load the app onto their device to test it in a real-world environment.

### Future Iteration's Goals
Moving forward, our goal is to be able to store, load, create/delete plant entries from the user. Once we get this goal completed, we will have the core functionality of our app finish. Then we will be able to move onto other future goals listed in the proposal, like sharing between users.


Team
====
### Team Member's Roles
Since there is not designated project manager for our team, we have all been contributing like a project manager. This mostly means that if a team member saw something needing to be fixed, then an issue was created and assigned to themselves or the person who could most likely finish it, and was then worked on. This role for each team member, although unorthodox, has been effective with getting issues completed and having each team member contribute.

### Moving Forward
Continuing forward with our project, we will stick with this format of each team member acting as a pseudo project manager.


Project Management
==================
### Product on Schedule?:
Project is on schedule

### Updates to schedule:
In our original project proposal, we were projecting for possible iOS implementation of the app. However, this will most likely not be completed before the project deadline.


Reflections
===========
### What went well?
Our team structure has actually been fairly successful. With each member acting as a project member, we are all contributing to programming, managing, and reaching deadlines. The software aspect has been able to stay on track and we're completing each milestone to our we originally projected it, with minor changes.

### What didn't go well?
Although the team structure was been successful, we haven't utilized creating issues for each change to the project we make. This isn't too bad, but it does make the project easier to manage when all issues are in a single place in the project repo.

### What will be done differently in next iteration?
We will try to utilize creating specific issues for the various code changes we make. Doing this will allow for viewing changes to the project more easily and see what is being worked on.
