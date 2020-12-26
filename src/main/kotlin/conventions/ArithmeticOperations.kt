package conventions

fun main() {
    val pointA = Point(1, 1)
    val pointB = Point(2, -2)

    println(pointA + pointB) //Using extension function operator "plus" from class Point
    println(pointA * pointB) //Using extension function operator "times" from class Point

    println(pointB * 2) // Using extension function operator "times" from class Point
    println(2 * pointB) // Using extension function operator "times" from class Int



}

//Extension function to overload operation +, reserved as "plus"
//The method needs the keyword "operator" to refer to any of the defined operators
operator fun Point.plus(other: Point): Point {
    return Point(this.x + other.x, this.y + other.y)
}

operator fun Point.times(other: Point): Point {
    return Point(this.x * other.x, this.y * other.y)
}

operator fun Point.times(times: Int): Point {
    return Point(x * times, y * times)
}

operator fun Int.times(point: Point): Point {
    return Point(this * point.x, this * point.y)
}