
[![LinkedIn][linkedin-shield]][linkedin-url]

<p align="center">
  <h3 align="center">IOT UArt Simple App</h3>
  <p align="center">
   	IOT Project with uart simple full implement, include in this project is hardware implement - Mircobit, software implement - Java App in Android Studio, Server implement - MQTT server
  </p>
</p>

## Gateway app
![20](/images/20.png)

## Dashboard
![21](/images/21.jpg)
![22](/images/22.jpg)
![23](/images/23.jpg)

<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
	<li><a href="#hardware-implement">Hardware Implement</a></li>
	<li><a href="#software-implement">Software Implement</a></li>
	<li><a href="#server-implement">Server Implement</a></li>
	<li><a href="#contact">Contact</a></li>
  </ol>
</details>


## About The Project

In this project, i create the full stack IOT project include hardware implement, software implement and Server implement. in each implement i will quick in 3 section bellow. The main program this project solve is: Taking the data from a several number of "sensor node" and send this to "Gateway" (connect between Center node and Gateway app) after processing this data "Gateway" send this processed data to "MQTT" to showing this in the other "Dashboard" through Application in Java language connect to "MQTT" server.

![2](/images/2.png)

## Hardware Implement

![19](/images/19.png)

In this project, I using 2 Microbit node, which 1 using for sensor and other for center node

* Sensor node
Sensor node Microbit play roll of sensor, collect datafrom sensor in microbit are there tempurature and light level in real time (every 5 second), after that
transfer this information to Center node. In addition, this node have to receive control code from Center node for controller
![3](/images/3.png)

* Center node
Center node Microbit play roll of center, collect data from Sensor Nodes through the radio connection (in channel 20), after that transfer this information to gate from Serial. In addition, this node have to receive control code from Serial and push it back to
Sensor Nodes
![4](/images/4.png)

## Software Implement

in this part, i using JAVA language to making app for "Gateway" and "Dashborad" In this project, I using Android for gateway, that mead i connect Center Node to gateway - Android and build application to make UART connection and collect all Serial write from Center node

* How to parse the data and publish the data to the server
The idea is, Data collect real time in Sensor node will
be adding in String have template is:
"te1(temperature number)li2(lightlevel number)$"
![5](/images/5.png)
I using regex to file matching data from Sensor Node
to Center Node to UART and getting group for puslish this information to MQTT server

* How to get control code from the server and send this to Sensor Node
The idea is, when control code send from the MQTT server (in function messageArrived), this gateway will send this information through Center Node to Sensor Node
![6](/images/6.png)

* I using navigation menu to be layout of this applicaiton so i will devide this app to fragment for "Dashboard" App
	* Home Fragment 
	This fragment there is has 2 grapview 1 for temperature and other for ligh level
	![7](/images/7.png)
	In way collect data, i using the way i collect data from gateway with regex form to find group match data from MQTT server
	![8](/images/8.png)
	With listt and listl is list of number collect data and send this data to grapview during 5 second from timer function
	![9](/images/9.png)
	* Number Fragment 
	This fragment there is has 2 ProgressBar (custom circle shape) 1 for temperature and other for ligh level
	![10](/images/10.png)
	In way collect data, i using the way i collect data from gateway with regex form to find group match data from MQTT server. with progress view when new data coming from the new number will show in this progress view
	![11](/images/11.png)
	* Control Fragment 
	This fragment there is has 2 Button 1 for Heart control and other for Home control
	![12](/images/12.png)
	when click 2 of this button the is publish seperate control code to MQTT server
	![13](/images/13.png)

## Server Implement

 MQTT Server Azure IoT Hub MQTT Connection The IoT Hub is the gateway for data to the Microsoft Azure Portal. Data can be exchanged with the IoT Hub via MQTT. We show the MQTT connection of the IoT Hub in a step by step tutorial with the OPC Router as MQTT Client. The Microsoft Azure Cloud is one of the leading cloud environments on the market. The Azure Portal provides many ready-made services that can be easily connected. Cloud applications are created by interconnecting the finished components. One of
these components is the IoT Hub, which serves as the most important bidirectional connector for external data sources and IoT devices. With the OPC Router, the IoT Hub and thereby the Azure Cloud can be connected to write data to the Azure Cloud, but also to obtain data from the Cloud for the field level. The connection of the IoT Hub is realized with the OPC Router MQTT Client Plug-in. The transfer of data at the field level or the data procurement for the cloud is implemented with the various plug-ins of the OPC router.

* Topic Subscribe and Publish

	* Implement connection
	![14](/images/14.png)
	
	* Connection Function
	![15](/images/15.png)
	
	* Subscribe Function
	![17](/images/17.png)
	
	* Publish Function
	![18](/images/18.png)

## Contact

Hieu Nguyen - [Linkedin](https://www.linkedin.com/in/hieunguyen-dev/)

Linkedin: https://www.linkedin.com/in/hieunguyen-dev/
Email: hnhieu979@gmail.com
Phone: 0927931496
Facebook: https://www.facebook.com/hieu.nguyenmixed

Project Link: [https://github.com/HieuNguyenHuu/IOT_UArtSimpleApp](https://github.com/HieuNguyenHuu/IOT_UArtSimpleApp)


[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/hieunguyen-dev/


