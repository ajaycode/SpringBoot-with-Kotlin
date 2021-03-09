# Use of a default layout across all pages

A default template is used and each of the Controller methods plug their views into the template using the layout:decorator="~{layout/layout}" in each of the pages.
The content that must be posted into the layout  (eg. subtract.html) is embedded within <div class="container-fluid" layout:fragment="content"> ...</div> elements.
The view (eg. subtract.html) contains a pointer to the layout file - eg. layout:decorator="~{layout/layout}"


The template is defined in layout/layout.html and has a <div layout:fragment="content"></div> and all of the content from the view is placed within this div.

To use these templates/layouts,  include the following dialect in gradle build files: _implementation ("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")_

In case this project does not build using IntelliJ,  build using gradlew on the command line.  Instructions are:
gradlew clean
gradlew build -x test
gradlew bootRun

To view the content, navigate to:
http://localhost:8080/home and
http://localhost:8080/subtract?num1=47&num2=27

