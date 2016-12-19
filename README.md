# AM

`am` makes it super-easy to unit-test Java Servlet API code by providing various mock
implementations and helper classes.

# Test

The library provides convenience utility methods for testing for:

* Testing JSP tags - short-cut way
* Testing JSP tags - full-fledged way
* Testing of Servlet filters

## To test the output of a simple JSP tag

```java
AmTagLibTestHelper.testTagOutput(MyCustomJSPTag.class, // the class implementing custom tag
	expectedOutputWrittenToJspWriter,  				   // the expected String response
	new GenericConsumer<MyCustomJSPTag>() {            // set the values before invocation
	
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

## To test a given JSP tag we essentially need to do:

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

## To test a Servlet Filter

The code below is used to test `MyCustomFilter` by passing the relevant wired `ServletRequest`
and `ServletResponse` objects making sure that the internal `FilterChain` supplied is invoked.

```java
// obtain an instance of the filter
MyCustomFilter filter = AmServletFilterTestHelper.getFilterForUnitTesting(MyCustomFilter.class);

// create request and response objects as filter will need them
// the AmHttpServletRequest.getDefault() method returns a request for a server
// deployed on localhost on port 80, and being hit with same machine where
// the servlet context is `context` and the path is `/home.html`
AmHttpServletRequest request = AmHttpServletRequest.getDefault("home.html");

// the response object to which filter will write
AmHttpServletResponse response = new AmHttpServletResponse();

// filter invocation
AmServletFilterTestHelper.assertFilterChainInvoked(filter, request, response);
```

A similar method `AmServletFilterTestHelper.assertFilterChainNotInvoked` is available that
checks that during filter execution, the `FilterChain` was not invoked.

# Mock availability

Dummy or mock implementations are available for the following Servlet API classes:

* FilterChain
* HttpServletRequest
* HttpServletResponse
* HttpSession
* JspContext
* JspWriter
* PageContext
* Principal
* RequestDispatcher
* ServletRequest
* ServletResponse

Along with are available the following support classes to aid in testing:

* AmExceptionHandler - Exception handler to be used in conjunction with `AmPageContext`
* AmFowwardOrIncludeHandler - handles forward/include calls in `AmPageContext`
* AmUrlEncoder - a simple URL encoder to help in encoding URLs  in `AmHttpServletResponse`
* ByteArrayServletInputStream - can be used with the `AmServletRequest` to provide input
* ByteArrayServletOutputStream - can be used with `AmServletResponse` to capture output

# Downloads

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
Copyright (c) 2016, Sandeep Gupta

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
