## Types

### Basic Types

In Scala, you have following basic types:

- Byte
- Short
- Int
- Long
- Char
- String
- Float

All types which can be constructed use those basic types in some form. One could say that those types are always available, in every programming language, either built in or emulated of some sort. Don't think too much about them.

The type which is used very often is the type _String_, here are some examples how to use it:

~~~
val s0 = "hello world"

val s1 = s"$s0 !"
println(s1)              ... prints 'hello world !'

val s3 = s"""|
             |this is 
             |a multiline 
             |string
             |
             |""".stripMargin

println(s3)                          
~~~

Scala supports a handy "in place replacement" of variables by prepending the string definition with an 's', like demonstrated above in the assignment _val s1 = s"$s0 !"_. The important character is '$', which tells the compiler that the following identifier is a value which is currently _in scope_. 

_In scope_ means that the value is defined and visible at this point of the program.

Another interesting property of Scala's string handling routines is the multiline example. In the above example, you can see that the string definition uses three '"' characters to denote a multiline string.

The '|' at the beginning of the string denotes the 'left side' of the screen, which is stripped away by the _stripMargin_ method.

Ok, thats quite much information; like what is going on here? Why can we call a method on a _String_? (We are talking about the _stripMargin_ method ;-) ) 

In Scala, you can enrich types with your own methods, without having access to those types. Like that the Scala standard library has enriched the _String_ class with certain handy methods. The neat thing is that this is done in a library, and is not baked in in the Scala language itself which is a big difference to other languages.

This means that just by changing a library, which is written in Scala, one can enrich the user experience of a consumer of this library. This is also a reason why _Scala_ got its name - it allows growing a rich ecosystem by exploiting simple but powerful concepts.

### Everything is defined in a library

This concept of 'is implemented in a library' applies to almost everything in the Scala language. For example, operators like _+_ or _-_ are implemented in a library as well, as methods on the corresponding type. 

This is a glimpse on the power and expressiveness Scala has to offer as an ecosystem, but we will stop here. The takeaway is that you can write expressions like the following, just as you would expect:

~~~
1.0 + 2.0
2 * 4
"a" + "b"
4 / 3
5.0 / 1.3
1 > 2
1.0 >= 1.0
'a' > 'A'
val amused = !false
true || false
true && false

~~~

What would you expect of a function which 'adds' two strings? What is the result of a division if you use just Integer type? Try out the examples above in the _REPL_ and compare the result to your expectation.

A thing or two about operator precedence: It is well defined which operation takes precedence over another, but it is totally ok to just wrap your operation in brackets:

~~~
((a + b) * 3) > 71
5.0 % 3 * 7 / 1 + 1
~~~

... just in case you are not sure which operation is performed first. 




  