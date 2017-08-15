## Classes 

The examples following below should be tried out on the _Scala REPL_, which is an easy way to try out small code snippets. You can start the _REPL_ just by typing in _scala_ on the command line, given that you've installed Scala correctly.

A class is a blueprint for object instances. 

~~~
class A
~~~

Defines a class _A_ without any members.

~~~
class B(val a : A)
~~~

Defines a class _B_, with a member named _i_ of type _A_. You observe that unlike in other languages, the name of the attribute comes first, then there is a colon, and after that there is the type of the attribute. In our case the name of the attribte is _a_, with the type _A_.

You can instantiate the classes above with the following statements:

~~~
new A
new B(new A)
~~~

or, you could assign instances of the class to values and reuse those:

~~~
val a = new A
new B(a)
~~~

So, for example, you could create an class representing a person like this:

~~~
class Address(val street: String)
class Person(val first : String, val address : Address)

val address = new Address("Alte Poststraße 149")
val person = new Person("Bob", address)
~~~

## Case classes

If you prepend the _class_ keyword with _case_, you get so called 'case classes', which are most commonly used:

~~~
case class A(s: String, code : String, nationality: String)
case class P(first: String, second: String, address : Address)

val address = Address("Alte Poststraße 149", "8020", "AT")
val person = Person("Bob", "Smith", address)
~~~

You can see that by defining the classes as 'case classes', you can omit the _new_ keyword when you want to create new instances of a class. Also, the _val_ keyword when defining attributes of the class are not necessary. Furthermore, in the background, the Scala compiler generates a _equals_ and _hashcode_ method for our class, which makes them easily comparable:

~~~
case class A(i : Int)
A(1) == A(1)          ... evaluates to true
~~~

This may sound unfamiliar if you have never programmed on the JVM, but implementing valid comparisons between object instances is somewhat tricky. If you think a bit about this topic, you will soon realize that this rather 'trivial' aspect of programming already has a great potential messing things up. See following code:

~~~
class A(val i : Int)
val a = new A(1)
val b = new A(1)
a == b  .... yield false

case class B(i : Int)
val a = B(1)
val b = B(1)

a == b ... yields true
~~~

In this code snippet, the class _A_ is not defined as _case class_. As such, the memory references are compared against each other; clearly in the line _a == b_ one gets a _false_ result since both of the values (_a_, and _b_) have different memory addresses. 

In the second example, with _case class B_ however, we compare the classes by inspecting their attributes - in this case we only have one attribute, called _i_, which is set to _1_ for both _a_ and _b_. 

The comparison of _a_ and _b_ therefore yields true, since, _1_ equals _1_. 

_Case classes_ are foundational for our purposes and the programming style we'll pursue in this course. They are omnipresent in typical Scala code bases. For example, they are of great use when doing pattern matching.

## Methods on classes

In Scala on can define methods in (case) classes:

~~~
case class A(name: String) {

  def greet() : Unit = println("hello " + name)

}

A("world").greet()      ... prints out 'hello world'
~~~

In this example we have a method which returns nothing, the proper return type is _Unit_ in this case. _Unit_ can be compared with _void_ in other programming languages (_C_ for example).

## Access Modifier

In Scala, if not stated otherwise, the visibility of methods or attributes is always _public_. For our simple applications we won't need access modifiers, but in real world systems those modifieres help to enforce [_information hiding_](https://en.wikipedia.org/wiki/Information_hiding).

Example:

~~~
case class A(i : Int) {

  private def foo() : Unit = println("internal")
  
  foo()
  
}

A(10) ... prints 'internal'

val x = A(10)

x.foo() ... won't compile
~~~

The method _foo_ can only be called inside A and is not reachable outside of the scope of this class.

## Immutability

During this course, we will hit very often the need to modify existing data structures, and if one is used to do imperative programming, programming with immutable data can be some kind of alienating.  

_case classes_ support programming with immutable data structures, they greatly simplify working with immutable data. They can be used to encode ADT's, so called _algebraic data types_.

One method we'll use very often with _case classes_ is the _copy_ method:

~~~
case class A(i : Int, b : Boolean)
val a = A(10,false)
println(a.b)                  ... prints 'false'

val x = a.copy(b = true)
println(x.b)                  ... prints 'true'
~~~

As one can see, the _copy_ method can be used to just take the whole original instance, and replace some selected attributes with new values.

If you have wondered about the efficiency of copying large instances: Scala is implemented in a way that this is not an issue, since internally the objects are not really copied, but layed out in memory in a clever way such that no copying of data is performed in reality, but copied objects reference the same data.

Generally speaking, one can say that programming with immutable data is easier to reason about and also makes it simpler to parallelize algorithms and runtime implementations.

Like mentioned elsewhere, Scala perfectly supports programming with mutable data as well. This is, however, not recommended. It makes reading the code more difficult and is often a source of bugs. Try to avoid that style whenever possible. 

 
