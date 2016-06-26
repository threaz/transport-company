<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">1. General project overview</a></li>
<li><a href="#sec-2">2. Design patterns used in project</a></li>
<li><a href="#sec-3">3. Further use of the program's components</a></li>
<li><a href="#sec-4">4. User running the application</a></li>
<li><a href="#sec-5">5. Running the application</a></li>
</ul>
</div>
</div>

# General project overview<a id="sec-1" name="sec-1"></a>

My project models is a simple client application that enables user to book airplane or coach travel. It uses model-view-controller architecture to achieve this in the most efficient way. For now, the application can run for only one user, but multiple user login can be achieved by running simple server application with all information about travels and registered users(this is my goal for the future). User of the program is able to communicate with program via graphical interface. Each of the events raised by the user(View), which is mostly a button click, generate the message that is handled by the ViewController(Controller) implementing all the logic connected with data retrieval. By means of the controller the application manages the resources stored in Model. Thus, preventing View from directly talking to the Model. Implementing MVC in Swing wasn't easy, because it doesn't serve convinient ways of two-way communication between objects. I modeled the Controller as ActionListener which then can be distributed to View objects. Each event generated by the user is signalled with actionPerfomed method. Controller responds to the source of the message by the object method, userLoggedIn() for example.

# Design patterns used in project<a id="sec-2" name="sec-2"></a>

-   singleton (ReservationSystem), because there should be only one system that manages user's orders and available travels
-   observer (ViewController)
-   bridge (ClientDatabase), hides the implementation of the database, only the methods from presented interface are used in the programm enabling replacing current implementation in the future

# Further use of the program's components<a id="sec-3" name="sec-3"></a>

As the company using the application will grow, there can appear different possibilities of travel. For example the firm may be offering boat trips. Boat class can be easily implemented as extending already existing class MeanOfTransport. It can provide more services and details than MeanOfTransport class so additional methods and properties may be added.

# User running the application<a id="sec-4" name="sec-4"></a>

Basic session with my application includes the following steps:
1.  Registration of the user
2.  Logging in
3.  Searching for desired travel
4.  Creating the order by selecting places in the transport vehicle
5.  (Optional) Managing the orders(removing unwanted)
6.  Paying for the orders
7.  (Optional) Editing profile information of user

# Running the application<a id="sec-5" name="sec-5"></a>

    cd TransportCompany && ./run_program.sh
