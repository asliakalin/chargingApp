### This is the CS160 Programming 2B assignment readme file.


Outline of Views & Features:
# Views:
1. Splash View/Loading Screen
2. Car Selection View
3. Current Location Map View
4. Zipcode Location Map View
5. Charging Station Brief View
6. Charging Station Detailed Views
     - Overview Fragment
     - Contact Fragment
     - Amenities Fragment
     - Reviews Fragment
7. Add New Review Fragment

# Features:
1. Select a car in a horizontal recycle view 
2. See your current location
3. Search EV charging stations around a zip code you entered
4. Search EV charging stations around the current location
5. View all nearby EV charging stations on the map as markers
6. Click on markers to view brief details about the station:
7. Full name of station
     - Icon of the station
     - Address
     - Currently open/closed
     - Price
     - Distance from current location
     - Overall rating
8. Click on “More Info” button to see detailed station view with tabs:
     - Full name of station
     - Picture(s) of the station
     - Currently Open/Not
     - Open hours each day
     - Price Level
     - Address of the station
     - Current Waiting Time
     - Connector Types Available
     - Street View of the Station
     - Phone number + international phone number
     - Website (clickable)
     - List of Amenities
     - Overall Rating
     - Number of Reviewers
     - Reviews
9. Add reviews



Once the user clicks on the app, the progress bar goes from 0% to 100% while displaying the name of the app. Once the progress bar is full, the welcoming page greets the user and allows the selection of a car. Each item in the horizontal recycler view consists of the picture of the car, model of the car, and the type of EV it is. Once the user clicks “next” after making the car selection, the map shows the user its current location.

The user can either click on “Find Nearby Charging Stations” or enter a zip code or an address in the search bar and click “Search” to view EV charging stations in the wanted area. The first screenshot above is an example for finding nearby charging stations around the current location. The second and third screenshots are to view EV charging stations around the location marked by zip code 95472 and 94567 respectively. For user’s convenience, the entered zip code location is also viewed in the map as the yellow marker whereas the charging stations are marked with magenta markers. During the search, the user can also use the “-“ and “+” buttons to zoom in & out and click on the “current location” button on the bottom right to get centered around her current location again. When the user views all nearby EV charging stations on the map, she can click on the markers to view some information about the station.


The user can click on “More Info” button for any of the stations to view the detailed station information. The information rendered here is collected from the results of Places API details, street view, and place API photo requests. The detailed information about the stations consists of 4 tab views under the name of the station as the main title: Overview, Contact, Amenities and Reviews.

The Overview Tab includes pictures from this station (the example above has only one but up to 10 pictures are rendered here as a recycler view if there were more pictures) provided by google API photo requests, whether the station is currently open or not if provided by google API, the hours of operation for this station each day and time if provided by google API and the address of the station. Additionally there is information about price levels which google places API provides from a scale of 1-5. When this information is available, it is rendered under “Price level,” when it isn’t provided the section reads “No price level information is given” so it is not hardcoded, but it is rarely there. The hardcoded values under overview tab are current waiting time, adaptor types in this station and summary of amenities. These values aren’t provided by the google places api but I thought they would be essential and I’d get them from somewhere else if I had the time. Lastly, the overview section also has a StreetView of the station to see where it is located at. The StreetView is through the Street View Static API so it isn’t interactive but it provides a good intuition of what a user should look for in the location.

The “Contact” tab contains information about the station such as the address, phone number, international phone number and the website of the station. All of the information rendered on Contact tab is received from the google API. The website section has both the url seen and also contains a clickable web view of the website where users can directly interact with that navigates them out of the app and onto the website.

The Amenities Tab contains information about the available amenities in the station such as Wifi, root access, landline, parking, food and drinks and car assistance.

The information here is hardcoded since google API doesn’t provide these details. Lastly the Reviews tab shows the overall rating of the station, number of people who reviewed the station and the individual reviews saved.

Additionally, the plus button on the bottom right allows users to enter their rating for this station.The users can rate the station based on their overall experience, pick some tags that they associate with the station such as “clean” or “convenient” and lastly can enter any other comments and feedback they’d like to share including pictures before clicking submit to enter their feedback of this station.

