# Baking

This is project for [Udacity Android nanodegree](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801)
----


This app fetch some recipe data from Udacity network. (https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json).<br />
This data is given by Udacity.

It shows the recipe in multi screen like mobile phone and tablet.<br />
To implement this, I refer to the android document (https://developer.android.com/guide/practices/tablets-and-handsets.html)<br />
![Tablet](https://github.com/yoonhok524/Baking_UdacityProject/blob/master/screenshots/Screenshot_1501514241.png?raw=true)

You can select one of recipe and check ingredients and steps.<br />
To make below screen, I used ListFragment.<br />
![Tablet](https://github.com/yoonhok524/Baking_UdacityProject/blob/master/screenshots/Screenshot_1501514272.png?raw=true)

You can see any video in detail step view.<br />
ExoPlayer Library is used to show the video in android.<br />
This is very simple example to use ExoPlayer.<br />
![Tablet](https://github.com/yoonhok524/Baking_UdacityProject/blob/master/screenshots/Screenshot_1501514280.png?raw=true)

And you can create widget in home screen, so you can see the ingredients easily.<br/>
My widget is showing ingredients as collection data.<br />
So I implemented RemoteviewsService and RemoteViewsFactory.<br />
You can refer to the guide in here: (https://developer.android.com/guide/topics/appwidgets/index.html#collections) <br/>
![Tablet](https://github.com/yoonhok524/Baking_UdacityProject/blob/master/screenshots/Screen%20Shot%202017-08-01%20at%2012.02.22%20AM.jpg?raw=true)

<br/>
This app is  
  - using REST API to get the recipet data from Udacity network.<br/>
  - using Glide Image Library to show the Recipe image. (But actually there are empty url string in the recipe data from Udacity network, so it doesn't show any images.)<br/>
  - using ExoPlayer video Library to show the cook video.
  - creating App Widget<br/>
  - support multi screen (Mobile hand device, Large screen tablet)<br/>
  - containing simple espresso test<br/>
