# The Assignment
Use the Giphy API to create an Android app which allows users to search, view, favourite,
and unfavourite GIFs. Here are some links to the Giphy documentation and the endpoints
you will use:
Documentation https://developers.giphy.com/docs/
Trending GIF Endpoint https://api.giphy.com/v1/gifs/trending
Search GIFs Endpoint https://api.giphy.com/v1/gifs/search

# Features
● All GIFs in the app must be running (ie. in motion)
● The user must see a list of trending GIFs when launching the app
● The user must be able to type any search term into an search area on the first
screen and have the view refresh the list with the results of their search
● The user must see a grid of his/her favourite GIFs on the second screen
● The user must be able to add and remove GIFs to and from their favourites

# Specifications
● There should be one activity with two Fragments
● The Fragments should be swipeable using a TabLayout and ViewPager
## First Fragment
● Contains a search bar (EditText, SearchView, etc.) at the top
● Contains a RecyclerView that displays searched GIFs in a single column list
● The RecyclerView should load trending GIFs if there is no search term provided
● Show a loading indicator when loading GIFs or refreshing the list
## Second Fragment
● Contains a RecyclerView that displays a grid (at least two columns) of the user’s
favourite GIFs stored locally on the device
## List/Grid Item
● Should contain a layout including a running GIF
● Should contain a button which allows the user to add or remove a GIF from their
favourites list
○ This button must also indicate if the GIF is a favourite or not

## Bonus
● Use a software architecture pattern (ie. MVP, MVVM, VIPER, etc.)
● Use Coroutines and Room
● Use Kotlin instead of Java
● Add pagination (infinite scrolling) to the GIF lists
● Add animations
● Add unit tests for your code

## Notes
● Please use Git as version control for your work
● Please push your work to an online personal repository (ie. Github, etc.)
● When your work is complete please send us a link to the repo and we will begin the
review process
● You are welcome to use any libraries that you desire
○ You might benefit from using a library to load the GIFs
● Feel free to ask any questions to help clarify the project
● Please apply the best code practices and architecture design for creating robust
and scalable applications to show off your best skills
● Please do not use the Giphy SDK for this project, we are looking for candidates to
display a solid understanding of REST APIs

# Feedback
## Positive:
 * You have written tests for search and favourite;
 * Tests are following the 'Given, When & Then' pattern;
 * You have created Base classes to handle common methods;
 * Implemented an abstraction to avoid memory leak by RX when view is destroyed
 * API key and BASE URL are stored on gradle
## Improvement:
 * A mandatory requirement was not implemented: "Should contain a button which allows the user to add or remove a GIF from their favourites list"
 * And the following mandatory requirement didn't work - "This button must also indicate if the GIF is a favourite or not" - when a GIF was unfavourited on Favourites tab
 * The list is cutting off last item;
 * Keyboard is only triggered when tapping search icon (instead of entire view)
 * Search field is only triggered when tapping send (instead of search when typing)
 * App is not handling empty Search properly
 * Gif image resizes when tapped
 * Gif removal is not intuitive: A long press event was implemented