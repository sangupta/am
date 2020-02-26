# AM

[![Travis](https://img.shields.io/travis/sangupta/am.svg)](https://travis-ci.org/sangupta/am)
[![Coveralls](https://img.shields.io/coveralls/sangupta/am.svg)](https://coveralls.io/github/sangupta/am)
[![license](https://img.shields.io/github/license/sangupta/am.svg)](https://choosealicense.com/licenses/apache-2.0/)
[![Maven Central](https://img.shields.io/maven-central/v/com.sangupta/am.svg)](https://search.maven.org/search?q=g:com.sangupta%20AND%20a:am&core=gav)


`am` makes it super-easy to unit-test Java Servlet API code by providing various mock
implementations and helper classes.

The library is tested on the following JDK versions:

* Oracle JDK 11
* Oracle JDK 9
* Open JDK 11
* Open JDK 10
* Open JDK 9
* Open JDK 8

# Usage

The library provides convenience utility methods for testing for:

* Testing JSP tags - short-cut way
* Testing JSP tags - full-fledged way
* Testing of Servlet filters

## Testing a simple JSP tag

```java
AmTagLibTestHelper.testTagOutput(
    // the class implementing custom tag
    MyCustomJSPTag.class,
    
    // the expected String response
    expectedOutputWrittenToJspWriter,

    // set the values before invocation
    new GenericConsumer<MyCustomJSPTag>() {
	
        public boolean consume(MyCustomJSPTag tag) {
            // set the properties of the tag
            tag.setProperty1(prop1);
            tag.setProperty2(prop2);
	
            // and so on...
			
            return true;
        }
    }

);
```

### Testing a custom JSP tag

```java
// let's say we are testing a Base64Tag class
// that writes the base-64 encoded/decoded value

// create a tag-instance that is wired for unit testing
Base64Tag tag = AmTagLibTestHelper.getTagForUnitTesting(Base64Tag.class);

// set any property that is required by the tag
tag.setEncode(value);

// execute the tag - this method only converts checked exceptions to runtime ones
AmTagLibTestHelper.doTag(tag);

String expectedEncoded = "... the actual base64 encoded value ...";

// the method converts any value written to JspWriter into String in null-safe fashion
String actualEncoded = AmTagLibTestHelper.getJspWriterString(tag);

// compare the values
Assert.assertEquals(expectedEncoded, actualEncoded);
```

### Testing a Servlet Filter

The code below is used to test `MyCustomFilter` by passing the relevant wired `ServletRequest`
and `ServletResponse` objects making sure that the internal `FilterChain` supplied is invoked.

```java
// obtain an instance of the filter
MyCustomFilter filter = AmServletFilterTestHelper.getFilterForUnitTesting(MyCustomFilter.class);

// create request and response objects as filter will need them
// the AmHttpServletRequest.getDefault() method returns a request for a server
// deployed on localhost on port 80, and being hit with same machine where
// the servlet context is `context` and the path is `/home.html`
MockHttpServletRequest request = MockHttpServletRequest.getDefault("home.html");

// the response object to which filter will write
MockHttpServletResponse response = new MockHttpServletResponse();

// filter invocation
AmServletFilterTestHelper.assertFilterChainInvoked(filter, request, response);
```

A similar method `AmServletFilterTestHelper.assertFilterChainNotInvoked` is available that
checks that during filter execution, the `FilterChain` was not invoked.

## Mock availability

Dummy or mock implementations are available for the following Servlet API classes:

* [FilterChain][1]
* [HttpServletRequest][2]
* [HttpServletResponse][3]
* [HttpSession][4]
* [JspContext][5]
* [JspWriter][6]
* [PageContext][7]
* [Principal][8]
* [RequestDispatcher][9]
* [ServletRequest][10]
* [ServletResponse][11]

Along with are available the following support classes to aid in testing:

* [MockExceptionHandler][12] - Exception handler to be used in conjunction with [MockPageContext][7]
* [MockFowwardOrIncludeHandler][13] - handles forward/include calls in [MockPageContext][7]
* [MockUrlEncoder][14] - a simple URL encoder to help in encoding URLs  in [MockHttpServletResponse][3]
* [ByteArrayServletInputStream][15] - can be used with the [MockServletRequest][10] to provide input
* [ByteArrayServletOutputStream][16] - can be used with [MockServletResponse][11] to capture output

## Changelog

* Current Snapshot
  * Renamed classes to use `Mock` when they are mocking a JRE class
  * Headers are now case-sensitive in [HttpServletRequest][2] and [HttpServletResponse][3]

* Version 1.1.0 (14 May 2019)
  * Updated field modifiers to `public` to allow access while assertions
  
* Version 1.0.0 (21 Dec 2016)
  * Initial release

## Release Downloads

The latest release can be downloaded from Maven Central:

```xml
<dependency>
    <groupId>com.sangupta</groupId>
    <artifactId>am</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Snapshot Downloads

The library is currently under infancy and being updated continually, and thus has not
been released. However, for development purposes you may use [JitPack](https://jitpack.io)
to pull in the latest snapshot build.

For **JitPack**, add the following `repository` to Maven,

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

And then, add the dependency as,

```xml
<dependency>
    <groupId>com.github.sangupta</groupId>
    <artifactId>am</artifactId>
    <version>-SNAPSHOT</version>
</dependency>
```

## Versioning

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, 
`am` will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.

## License
	
```
am: Assert-Mocks for unit-testing Java servlet API code
Copyright (c) 2016-2020, Sandeep Gupta

https://sangupta.com/projects/am

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


[1]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockFilterChain.java
[2]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockHttpServletRequest.java
[3[: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockHttpServletResponse.java
[4]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockHttpSession.java
[5]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockJspContext.java
[6]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockJspWriter.java
[7]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockPageContext.java
[8]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockPrincipal.java
[9]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockRequestDispatcher.java
[10]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockServletRequest.java
[11]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/MockServletResponse.java
[12]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/support/MockExceptionHandler.java
[13]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/support/MockForwardOrIncludeHandler.java
[14]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/support/MockUrlEncoder.java
[15]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/support/ByteArrayServletInputStream.java
[16]: https://github.com/sangupta/am/blob/master/src/main/java/com/sangupta/am/servlet/support/ByteArrayServletOutputStream.java
