# Cineteca_ManageDB
Project Cineteca, utilities for managing DB

Hi all!

This Java batch provides to insert into a MySQL DB informations about movies stored in DVD.

## RunnerExcelToDB

### Step 1.

Read XLSX file (by num row range) and identify the IMDB id

### Step 2.

Call OMDB service with IMDB id and Token to create a JSON file of every single movie.
The JSON file is converted to a structured POJO Object with the informations of Movie and Ratings

### Step 3.

Save the information into DB MySQL
