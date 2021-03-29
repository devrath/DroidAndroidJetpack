![Banner](images/Logo-new.png)

# Android Jetpack

![Jetpack Navigation Components](images/android-jetpack.png)
---


| Quick Reference |
| --- |
| [Navigation](https://github.com/devrath/DroidAndroidJetpack/blob/master/README.md#jetpack-navigation "Navigation") |
| [Life-cycle aware component](https://github.com/devrath/DroidAndroidJetpack/blob/master/README.md#lifecycle-aware-components "Life-cycle aware component") |
| [Data Store](https://github.com/devrath/DroidAndroidJetpack/blob/master/README.md#data-store "Data Store") |
| [Live Data](https://github.com/devrath/DroidAndroidJetpack/blob/master/README.md#live-data "Live Data") |

---


#### What is Android Jetpack library?

* Android Jetpack is a colletion of libraries. It helps Android developers to them solve various tech tasks during the development process. Such tools may accelerate the process of app building and prevent from making a large number of possible bugs. 

* It is a toolkit that is used by our developers to create Android apps for a short period of time without making much effort. This pack includes libraries and special guide. Jetpack has special components that connect the so-called Support Library and Architecture Components. There are four categories that are formed from components mentioned above like Architecture, UI, Foundation, and Behavior.

* Android developers are very much aware that managing the life cycles is not an easy task in android, This collection of libraries help in managing activity lifecycle so that developers can focus more on logic. The libraries take care of handeling configuration changes. Also helping in preventing memory leaks. collectively the complex things are made simple. 

#### Here is a video below describing about android jetpack

<p align="left">
<a href="https://www.youtube.com/watch?v=r8U5Rtcr5UU"><img width="380" height="220" src="http://i3.ytimg.com/vi/r8U5Rtcr5UU/maxresdefault.jpg" 
alt="Developers" /></a>
</p>

---
<h2>Jetpack Navigation</h2>

<p align="center">
  <img width="400" height="200" src="https://github.com/devrath/DroidAndroidJetpack/blob/master/images/jetpacknavigation.png">
</p>


#### What is Jetpack navigation
* This component handles the user-app journey. When the user navigates in the application, the user will go from one screen to another screen based on the complexity of application. 
* Earlier we used to use intents to move from screen to screen and the arguments to pass the sata along with it, Now navigation makes it much simple.
* It uses the pattern where there is one activity nd multiple fragments.
* It creates claees in background and via calling functions, we can move from screen to screen.
* It handles complex cases like bottom tabbed navigation and App drawers, These navigation is handeled in a seamless way and developers do not have to worry about it.

#### Benifits of jetpack Navigation
* Handles fragment transaction by avoiding inconsistensies and memory leaks
* Handles device back and up buttons
* Handles back-stack
* Handles argument passing
* takes care of transistions animations
* Easy way to implement deep-linking

#### How does it work 

<p align="left">
  <img width="400" height="200" src="https://github.com/devrath/DroidAndroidJetpack/blob/master/images/navigationgraph.png">
</p>

* In the image above we can see that there is representation of flow based on user actions.
* There is a `activity` -> `This activity has a container for the fragment` -> all the screen flows reside in this container
* In the image above `title_screen` and based on the user action there can be two possibilities of navigation and so on.
* Here important thing to note is `backstack` and `navigation` is taken care by the library, no matter how much complex the navigation hierarcy is, developer can rely on the navigation library.

#### Demo represents how the navigation is used in
 * **[Navigation Drawer Activity](app/src/main/java/com/demo/code/navigation/activities/NavigationDrawerActivity.kt)**
 * **[BottomNavigation Activity](app/src/main/java/com/demo/code/navigation/activities/BottomNavigationActivity.kt)**
 * **[TwoFragmentContainerActivity (To show how the app reacts in such scenario)](app/src/main/java/com/demo/code/navigation/activities/TwoFragmentContainerActivity.kt)**

---
<h2>Lifecycle-Aware Components</h2>

<p align="center">
  <img width="400" height="200" src="https://github.com/devrath/DroidAndroidJetpack/blob/master/images/android_jetpack_lifecycle.jpeg">
</p>

#### what are life-cycle aware components
* Life cycle aware components perform actions in response to a change in the status of another component.
* Components can be such as `activities` and `fragments`
* They help to produce a better organized and light weight code which is easy to maintain.
* Most of app components that are defined in the android framework have life cycles attached to them. life cycles are managed by the `operating system`, If the aplication dosen't follow the rules of the `os`, There may be possibility of memory leaks

#### Building blocks of life-cycle aware components

The life-cycle aware components are made of 3 main parts 

| life-cycle | life-cycle owner | life-cycle observer |
| --- | --- | --- |
| It is a class that holds the information of the information of lifecycle states of components like fragment & activities | This defines the owner of the lifecycle. Activity/Fragment can be called as lifecycle owner | The lifecycle observer observes the state of the life cycle and here we can perform some operation |

#### Use-cases of life-cycle aware components
* Starting/Stopping the video buffering when the application starts and is visible, also stopping the buffering once application is destroyed
* Handeling the location updates, when the application is in foreground /background

#### What order the events are triggered of lifecycle aware components along with actual life-cycle events
* In the activity creation phase, the activity `life-cycle events` are triggered first followed by the `observer events`
* In the activity destruction phase, the `observer events` are triggered followed by the `life-cycle events`

#### Demo represents how the life-cycle aware component is used
* Here we have a activity that is playing a video using the exo-player, This exoplayer is life-cycle aware 
* Activity is binded to a life-cycle aware component
* We can observe all the logic of Exoplayer is moved into the life-cycle aware component even though the life cycle is lifecycle aware 
* This helps to organize our logic seperately 
* **[Exoplayer Activity](app/src/main/java/com/demo/code/lifecycle/activities/ExoplayerActivity.kt)**
  <->**[Lifecycle-aware component](app/src/main/java/com/demo/code/lifecycle/util/ExoplayerActivityObserver.kt)**
  
---
<h2>Data Store</h2>

<p align="center">
  <img width="400" height="200" src="https://github.com/devrath/DroidAndroidJetpack/blob/master/images/datastore.jpeg">
</p>

#### What is Data Store
* Data store is a component of data storage solution from android jetpack.
* It allows us to store the `key/value` pairs like shared preferences or typed objects with protocol buffers.
* Data store uses kotlin, coroutines and flow to store data asynchronously with consistency and transaction support

#### Why to use data store when we already have shared preferences
* `Shared Preferences` are `synchronous` and not main thread safe, But data store is safe to use in main thread since it uses `Dispatchers.IO` under the hood.
* `Shared Preferences` are not safe from runtime exceptions, But the `Data-Store` is safe from runtime exceptions
* There is a easy way to migrate from `Shared Preferences`


#### Type of Data Store 

| Preference Data Store | Proto Data Store |
| --- | --- |
| <p align="center"><img width="400" height="200" src="https://github.com/devrath/DroidAndroidJetpack/blob/master/images/prefdatastore.jpeg"></p> | <p align="center"><img width="400" height="200" src="https://github.com/devrath/DroidAndroidJetpack/blob/master/images/protodatastore.jpeg"></p> |

#### Demo represents how the data-store is used
* Here we have a fragment where we are storing a value entered in edit-text, on click of save button, We have used global scope here
* We are displaying the data from the `dataManager` helper class that gets the data from the data store via a flow and displays the data in the text view
* **[View](app/src/main/java/com/demo/code/dataStore/fragments/preferenceDataStore/PreferenceDataStoreFragment.kt)**
  <->**[ManagerClass](app/src/main/java/com/demo/code/dataStore/util/DataManager.kt)**
  
---
<h2>Live Data</h2>

<p align="center">
  <img width="400" height="230" src="https://github.com/devrath/DroidAndroidJetpack/blob/master/images/liveData.jpeg">
</p>

#### What is Live data
* `Live Data` is a holder of data that can be observed, Speciality of this is the unlike the regular `observable`, It is `life-cycle aware`
* `Live Data` forms a good communication between the data and the UI, By which change in the data, we can update the UI
* Since the `Live Data` is `life cycle aware`, we can avoid memory leaks and crashes caused due to when the activities are stopped

#### Type of Live Data

| Type | About |
| --- | --- |
| Live Data | * It can only be observed but cannot be set
 * It is a subclass of Object |
| Mutable Live Data | * The value can be set here 
* It is a subclass of `LiveData` |
| Mediator Live Data | * It is a subclass of `MutableLiveData` |
