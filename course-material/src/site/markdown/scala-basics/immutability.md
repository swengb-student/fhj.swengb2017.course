# Immutability

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

 

