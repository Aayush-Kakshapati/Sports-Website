# Sports Website

A web application that provides information about various sports around the world, including their rules, history, equipment, and famous players.

## Technologies Used

- Java 21
- Jakarta EE 10
- JSF (Jakarta Faces) 4.0
- MySQL 8
- PrimeFaces 13
- HTML, CSS, JavaScript
- Apache Tomcat 10.1
- Maven 3.9.9

## Project Structure

- **src/main/java**: Java source code
  - **com.sports.bean**: JSF managed beans
  - **com.sports.dao**: Data Access Objects
  - **com.sports.model**: Entity classes
  - **com.sports.util**: Utility classes

- **src/main/resources**: Configuration and resource files
  - **database**: SQL scripts
  - **messages.properties**: Internationalization resources

- **src/main/webapp**: Web resources
  - **WEB-INF**: Configuration files
  - **resources**: Static resources (CSS, JS, images)
  - ***.xhtml**: JSF pages

## Features

- Display information about various sports
- Detailed sports pages with rules, history, equipment, and famous players
- Search functionality for sports
- CRUD operations for sports and their details
- Responsive design


## Database Schema

- **sports**: Basic sport information
- **sport_details**: Detailed information about each sport
- **admin**: Admin data 

## Pages

- **Home**: Overview and featured sports
- **Sports**: Listing of all sports with search functionality
- **Sport Details**: Detailed information about a specific sport
- **About**: Information about the website
- **Contact**: Contact form and information
- **Login**: Admin authntication
- **Admin Dashboard**: Perform CRUD operations on sports data