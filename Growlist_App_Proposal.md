Intro:
	The idea for this project was inspired by a problem one of the team members has in keeping track of a large collection of plants. Spreadsheets and text files are fine for keeping inventory, but certainly have their limitations, such as the inability to add pictures and specific details, easily sort and omit certain items, and easily share and trade with other collectors. Many serious collectors have expressed a desire for a more complete solution, and we feel this is an area we could easily apply our skills.
With almost everyone having a mobile device that has features such as a camera and internet connectivity, we decided that a better solution could be developed for mobile platforms. Our solution would be implemented as an Android app that would allow users to keep a list on their phone, with details of each item in a collection, as well as add and delete items as needed. Additional features such as adding photos of their items or sharing, trading, or selling with other users could be added later and give the app a further advantage over current inventory keeping methods. A final version of the app would allow for the user to store their list on their phone, upload to the cloud (google drive, etc.), or even export to a standardized excel spreadsheet for ultimate flexibility. While a bit far fetched, it is possible such an app could replace other online resources such as forums and facebook pages currently popular among collectors. 
Our team is somewhat diverse, including members with some iOS experience, some Android experience, and some with no mobile experience at all. This project is well suited to our team as it is a good starting point for those with coding experience but wanting to learn mobile development. We believe that while this app will be very useful for a number of people, it is something that should be easily achievable with minimal mobile programming experience, and the way we have decided to implement features allows for a very incremental and stable development as we learn new skills. The goal of this project is not only to create an app as described above, but also for each team member to increase their proficiency in Android (and time permitting, iOS) development, project management, and working as a team. 

Customer Need:
	The work that already exists in this area includes an app that allows users to keep track of a collection of a specific type of plant (Nepy), an app that attempts to allow users keep track of any collection but lacks functionality that would be optimal for large collections (My Collection), and also includes general purpose applications such as text files or excel spreadsheets. 
	The gaps in this area are many for those who desire a simple, attractive, and useful app for large and diverse plant collections. First, an app such as Nepy would allow a user that only collects that type of plant to keep track of their collections in a useful way, but we are attempting to create something that allows for more diverse collections, as is common for collectors. Next, a general purpose collection app would lack important functionality for plant collectors, particularly if searching and sorting are not easily done by the user. Also, the specific app that exists, My Collection, organizes by the file name of the photo that the user associates with each item, which creates problems in sorting/searching, and makes photo uploads necessary for each item. Finally, general purpose apps such as text files do not contain any specialized functions for collectors, such as the ability to sort, easily add pictures, and to easily add details.
	Therefore, the novelty of our application is one that attempts to fill in the gaps aforementioned by creating a simple, intuitive way for users to store, browse, search, and associate pictures with large collections of plants. That is, to attempt to hit a level of functionality that a serious collector would require while not sacrificing a clean, attractive user interface that will make maintaining sizeable collections less laborious. Thus, we would essentially attempt to combine the strong points of the existing apps, while eliminating the weaknesses that would prevent many collectors from using them. In addition, should time permit, our app will scale into something even more novel, with features such as adding ways for users to trade from each others’ collections being on the table, should we find ourselves significantly ahead of schedule. Another example of an expansion that will add novelty to this project will be the expansion of this app to IOS.
                        


Proposed Solution:
Our application will allow the customers to store entire collection in a more convenient way.  The application will store all of the information about each plant in the collection in one place.  The information about the plants and a photo are stored in the app and the data can be accessed from a mobile device.  This offers the customer more flexibility when it comes to managing their plant collection. Having all of this information is useful for future development of the application. 
Our first goal in creating this application would be to get the basic features working.  This would mean the customer could download our application and store the names and information about his pant collection.  We will be able to scale our application and add new features that allow users more flexibility with their collection’s data. The next feature of the app would allow for photos to be uploaded with each plant in the collection.  
Features will be added for as much time as we have in the semester.  We currently have plans to implement ways for customers to share their data with others. This could be done by exporting it to another file type like excel, or even uploaded to google drive or some sort of cloud storage.  The app will initially be developed for android users, but we could create an iOS version if we had enough time. 
Another possibility of a feature that could be added once the initial build is a social feature. This would allow collectors to potentially send messages or parts of their collection to other users
The success of our application will be determined by how many people use the application.  The number of downloads would indicate that.  If a new feature was added an indicator of success would be the number of downloads.  As more features were added we would expect more downloads. We currently not tested the idea on anyone. 
	Our customer centric success is determined by how much we benefit the customers.  We are successful if our application saves the customer time and simplifies collecting.  We are able to measure this success by how much they use our application.  If additional features were added we could see whether or not they benefit the customers more.  
	
Technology:
	At its most basic, stripped down version, the app will be able to allow the user to create and update basic information about their plants. This will be the most important milestone to reach as it will be the backbone of building onto the app, and should be fairly simple to implement with tutorial code on lists and buttons in Android. Some improvements the user would like to have would be having the capability to upload a photo of a plant the user’s plant to the plant’s entry. Ideally, we would be able to open the phone camera and take a picture in the app, but at the very least, we would like to be able to import pictures from the gallery. Another improvement would be to have a “Share/Export” option for a user’s list. Having this option would make it simple for users to show off their collection or even allow users to sell and trade their collections.
The app’s final build should most importantly be able to allow the user to create and maintain a list of plants within the users collection. Along with each plant’s entry, there will be additional information that could be entered. This information will preferably be stored using an SQLite database saved locally which would allow for quick access and offline access for the user.
Since our goal is to build the app for Android use, the main software we will be using will be Android Studio. This will be the simplest way to build and test within the Android environment. With time permitting, we will develop an iOS version of the app and to do so we will be using Xcode to build for the iOS environment. Both Android Studio and Xcode have simulators built into them which makes for quick and easy testing of our app. However, we will also want to test the app in a “real-world environment” so we will also install the app on our personal phones. With simulating the environment for quick changes and testing on personal devices for major updates, we believe we will be able to test our app thoroughly. With both of these programs being the de facto programs for mobile app development within their respective operating systems, meaning they are very heavily supported and simple to use, these IDE’s will be great for completing the tasks at hand.