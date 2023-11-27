# COMP2013 - Snake Game
Author: Abdullah Tukur 
**Last Update: 31st October 2023**
# Introduction
This coursework is about maintaining and extending a re-implementation of a classic retro game called Snake. The new implementation has never been completed, but at least it runs, once it is set up properly. More information about the original Snake game and its history is available on [Wikipedia](https://en.wikipedia.org/wiki/Snake_(video_game_genre)). In addition, you will find many opportunities on the internet to play the game online (e.g. [Crazy Games](https://www.crazygames.com/t/snake)).

This coursework contributes 75% to your overall 20 Credit COMP2013 assessment mark and will be marked out of 100. It is a substantial piece of work, so please plan your time well. We recommend dedicating approximately 40-60 total hours to the coursework. We expect 30-40 hours of work for those aiming for a pass (40+) and 60 or more hours for those aiming for a first (70+). Please keep in mind that the skill level varies quite a bit in a large class of students, and the exact number of hours depends on your individual skill level. To help you, we have fewer required lectures in the last two weeks of term, and some lab sessions are dedicated to your coursework.


## COMP2013 Coursework Task Description
**Deadline:**
- Milestone 1 – Friday the 24th of November 2023
- Milestone 2 – Tuesday 12th of December 2023

**Assessment Criteria**
The marks will be split as follows (for details see Appendix 1):
- 10% for git use (e.g., push, branch, merge, providing .gitignore)
- 30% for refactoring
- 30% for additions
- 10% for documentation (UML + Software Documentation (readme file + source code))
- 20% for the demonstration videos (Milestone 1 and 2), explaining your: git + documentation, maintenance activities, and additions

To give you an idea about what we are looking for when we do the marking, we provide a draft marking scheme. Please note that this is only a guide for us and does not guarantee you marks when you have done certain things. There is always an aspect of quality that needs to be considered as well, which often accounts for up to 50% of the mark. We also reserve the right to revise the marking scheme (within limits), if we see the need for this during the marking process.

### Coursework Requirements
**Milestone 1 (10%)**
**1st Video presentation 3 minutes**
Preparation before doing the video you should do the following:
- Set up a PRIVATE git repository on the school's GitLab server and use it actively for version control activities. The URL is: [https://projects.cs.nott.ac.uk/](https://projects.cs.nott.ac.uk/) (MFA authentication required). IMPORTANT: You will have to add Horia Maior, Marjahan Begum, Damla Kilic, Jwan Shaban, Lewis Stuart, Warren Jackson and Weiyao Meng as Developer (not Guest!), otherwise we cannot mark your git activities!
- Set up the project in your chosen IDE. The setup should include all the files and classes from the source file given.
- Run the program and make sure you can play the game.
- Make an initial commit to the git repository.

As a part of initial maintenance understand the code. You can perhaps do the following and take notes (voice or written) when you do them:
- Try understanding the high-level structure of the software. Perhaps draw some UML diagrams.
- For example, try identifying where the main logics for movement or where the logic for updating the score? Write down some questions and try finding answers by exploring the code.
- Once you have a good understanding of the software, try drawing a class diagram of the software. Compare your drawings with the automatically generated class diagrams. Think about how you would like to change the class diagram based on the maintenance tasks given (Milestone 2). Produce a revised class diagram. Please note you may need to change the class diagram during maintenance task.
- Make some basic changes e.g., rename a class. You can choose what you like to change from the maintenance task (refactoring and additional functionalities). Use screen-capture software to record a video of not more than 3 minutes with the following marking details:
  - Give an overview of your git repository. Make sure to show the commits and commit messages and that you have assigned Begum, Horia, Horia Maior, Marjahan Begum, Damla Kilic, Jwan Shaban, Lewis Stuart, Warren Jackson and Weiyao Meng.
  - Show the class diagrams with relationships of the software which includes some details from the maintenance tasks. Show how you intend to make some changes in relation to the maintenance tasks.
  - Explain the source and your initial thoughts on some maintenance tasks( refactoring and additional functionalities). Here we will look for your basic understanding of the source code and some understanding of what maintenance tasks you have to carry out.
  - Video recording should not be more than 3 minutes. We will look for the quality of the video, clarity of the voice, clear images, clear navigation synch with clear explanation.

**Milestone 2 – Final deliverable (90%)**

[See the marking scheme for the individual requirements and attached .](COMP2013%20Marking%20Scheme.md)

**2nd Video presentation 7 minutes**
Use screen-capturing software to demonstrate your achievements.
- Show each of refactoring and additions activities.
- You should highlight the two achievements you are most proud of.
- The video must be maximum 7 minutes in length (a penalty is applied if it deviates too much).
- The video file must be saved in MP4 format (a penalty is applied if you use a different format).
- Your video submission must be called `SurnameFirstName_Demo2.mp4` and reside in the `COMP2013-SurnameFirstName` folder described above. Note that the demo video will be the primary way in which we assess whether or not your software is working.

## Assignment Submission and Organisation
This section describes which files need to be submitted for the assignment and how they should be organized. You are required to create a root folder called `COMP2013-SurnameFirstName`, where "Surname" is replaced with your surname, likewise for "FirstName". The `COMP2013-SurnameFirstName` folder contains (and organizes) digital copies of all the files that compose the assignment.

- **Milestone 1 - `SurnameFirstName_Demo1.mp4`**
- **Milestone 2 – ALL THE ITEMS BELOW.**
  - **Project Information:** A `README.md` file (max. 300 words and up to three screenshots) capturing relevant project information under the following headings:
    1. Project Title + Author Name
    2. Brief project description
    3. How to install and run the project
    4. How to use the project (i.e., how to play the game)
    5. Credits [if any third-party elements were used]
  - **Claims List:** An Excel spreadsheet, called `SurnameFirstName_Claims.xlsx`, using the provided marking scheme (the final version will be released during w/c 6 Nov) documenting your claims regarding your maintenance activities and additions, with a clear statement about how you did it and where we can find the evidence (e.g., providing names of classes and methods involved). This information needs to be provided in the "Justification of Claim: …" column of the Excel spreadsheet. The overall word limit is 500, so keep it concise. This is the most important document of your coursework, so take extra care when writing it. WARNING: If you do not mention it here, do not blame us later if we missed it.
  - **Design Diagram:** A file called `SurnameFirstName_Design.pdf` that contains a high-level class diagram that shows the structure of the final version of your game, considering only classes (excluding fields and methods unless they are relevant for understanding design principles/patterns), interfaces, relationships, and multiplicity. Additional UML diagram of your choice and rationalized (why and where).
  - **Project Implementation:** A zip file containing your ENTIRE LOCAL PROJECT FOLDER except for the "out" folder. It needs to be possible to OPEN AND RUN your project in IntelliJ. To avoid disappointment later, test your final version by unpacking and running your project on an alternative computer. This should help to uncover hardcoded path dependencies, which was a major issue in previous years. Name your zip file `SurnameFirstName_SourceCode.zip`, where JavaVersion represents the Java version you used.
  - **Source Code Documentation:** A copy of your generated Javadoc documentation is required. Javadoc produces a series of linked HTML web pages to facilitate the browsing of project implementations. The HTML web pages produced by Javadoc are to be placed in a folder called `javadoc` that resides in the `COMP2013-SurnameFirstname` folder described above. Recall that the Javadoc output contains a brief description of each class and a description of each method, including input and return parameters. The new Java classes you write use the following author tag convention: `@author FirstName Surname`. All modified Java classes from the previous code base use the following author tag convention: `@author FirstName Surname-modified`. In addition to reading your `Claims-SurnameFirstName.xlsx` file, we will look at the Javadoc to find out how you maintained and extended the game. If it is not obvious from there, we might miss it. Also, we have only a limited amount of time to look at each coursework submitted. So, please make sure to provide informative but concise Javadocs.

You should end up with the following components in your `COMP2013-SurnameFirstName` folder:
- `javadoc/...` (javadoc files)
- `README.md`
- `SurnameFirstName_Source_Code.zip`
- `SurnameFirstName_Claims.xlsx`
- `SurnameFirstName_Demo2.mp4`
- `SurnameFirstName_Design.pdf`

**Uploading your coursework to Moodle**
For this coursework, Moodle limits uploads to a single file of up to 250MB. Before you upload your coursework, please create a zip archive with the name `COMP2013-SurnameFirstName.zip` of your `COMP2013-SurnameFirstName` folder and then upload that zip archive to Moodle.

## Technologies and Tools
**Java Version + IDE:** You must use Java 17 or higher and JavaFX 17 or higher for the implementation. The project files you are submitting need to be compatible with IntelliJ.
**Version Control with Git:** To start, please download the given source code from Moodle. Set up a project in IntelliJ and embed the files that you just downloaded. Note that the zip file you downloaded from Moodle only provides source code and resources but no project files. It is good practice to ignore IDE-specific generated files for source control. Add a `.gitignore` file to your project, to help ensure that you follow this good practice as well. Next, set up a remote git repository at the school's GitLab server ([https://projects.cs.nott.ac.uk](https://projects.cs.nott.ac.uk)). It needs to be set to PRIVATE. Then follow the setup instructions provided in GitLab to "Push an existing folder" (i.e., do an initial push to upload files from your local to your remote repository). Now you are ready for coding with version control. When it comes to creating GIT messages, some advice can be found in Appendix 1.
**Object-Oriented Implementation:** The implementation needs to adhere to some rules. We require these rules because we want a good product to be delivered. When we look under the hood, we do not want to see a pile of scrap metal, but rather a finely tuned, carefully crafted machine. You are required to follow coding conventions.
**Swing Knowledge:** The legacy code uses Swing rather than JavaFX. It is not a requirement to translate the code into JavaFX, although you might want to attempt this if you are aiming for a very high mark. If you are not familiar with Swing or want to brush up your Swing skills, we recommend the following YouTube course: [Swing Tutorial](https://www.youtube.com/playlist?list=PLTMybUaeagJagT2qoftaf5CkCvgc3pBTn).

## Important Notes
**Source code:** This coursework is about maintaining and extending existing code. So, for the maintenance part, YOU HAVE TO USE THE CODE WE PROVIDE (`snakee-v7-src.zip`) as a basis and not write your own game from scratch or use source code from other campuses or the internet.
**Interview:** Make sure you understand what you are writing in your source code and the Javadoc. We reserve the right to briefly interview you if we think that you do not understand it.
**Changes:** Please note that we may make some small modifications to the coursework specs. We will announce these on Moodle and keep a change log there as well.
**Penalties:** Besides the late submission penalty, which follows UoN standards, there will be a penalty for each of the following offenses:
- Using an incorrect document format (e.g., Word instead of PDF)
- Using an incorrect video format (e.g., SWF instead of MP4)
- Failing to comply with naming conventions requested above

**Feedback:** Dr. Hoira and Dr. Begum will, of course, be happy to answer questions and give high-level interim formative feedback on your assignment. If you get stuck, please get in touch! However, we might refuse to answer very detailed technical questions or very general questions (like "What do you think about my project so far?").
**Tips:** Please be aware that there will also be a lot of useful tips and answered questions on the COMP2013 Moodle page in the Announcement section and in the Teams COMP2013-DMS(2023) Questions channel.

**Plagiarism:** You are gently reminded that we are at liberty to use plagiarism detection software on your submission. Plagiarism will not be tolerated, and academic offenses will be dealt with in accordance with university policy and as detailed in the student handbook. This means you may informally discuss the coursework with other students, but your submission must be your own work. Please also note that it is not permitted for you to copy and paste text from another source without correct referencing. If you are unclear about this process, please discuss with the module convenors during one of our lab sessions or at the end of a teaching session.

## Questions
Questions can be asked in person during the lab sessions and on Teams. Please read the questions
that have already been asked on Teams before you ask or post yours, to avoid duplication. We
will try to answer your questions as quickly as possible.


