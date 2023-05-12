
<h2 align="center">CXBOX Demo</h2>

<blockquote>
<div> 
<p align="center">
<h4 align="center">CXBOX - Rapid Enterprise Level Application Development Platform</h4>

<p align="center">
<a href="http://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat" alt="license" title=""></a>
</p>

<div align="center">
  <h3>
    <a href="https://www.cxbox.org/" target="_blank">
      Website
    </a>
    <span> | </span>
    <a href="https://www.cxbox.org/demo/" target="_blank">
      Demo
    </a>
    <span> | </span>
    <a href="https://www.cxbox.org/doc/" target="_blank">
      Documentation
    </a>
  </h3>

</div>



<h3>Description</h2>
<p>
CXBOX main purpose is to speed up development of typical Enterprise Level Application based on Spring Boot. A fixed
contract with a user interface called <a href="https://github.com/CX-Box/cxbox-ui" target="_blank">Cxbox-UI</a> allows backend developer to create
typical interfaces providing just Json meta files. Full set of typical Enterprise Level UI components included -
widgets, fields, layouts (views), navigation (screens).
</p>
</div>

<h3>Using CXBOX</h2>
<ul>
<li> <a href="https://plugins.jetbrains.com/plugin/19523-tesler-helper" target="_blank">download Intellij Plugin</a> adds platform specific autocomplete, inspection, navigation and code generation features.
</li>
<li>
 <a href="https://github.com/CX-Box/cxbox-demo" target="_blank">download Demo</a> and follow <a href="https://github.com/CX-Box/cxbox-demo#readme" target="_blank">README.md</a> instructions. Feel free to use demo as template project to start your own projects
</li>
</ul>
</blockquote>

# CXBOX Demo
## Prerequisites:

#####

* java 8+
* maven 3.6+
* node.js 14.4+
* npm 6.14+
* git
* docker
* docker-compose

## Getting started

* [download Demo](https://github.com/CX-Box/cxbox-demo) source code


* install dependencies and build back-end and front-end

```
mvn clean install -PUI
```

* start environment

```
docker-compose up -d
```

* run application

```
press green button in Application.java
```

## Usage

* Feel free to use this demo project as template to start your own projects!

## License

CXBox Demo is an open-source project with the [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0) license