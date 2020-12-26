package conventions

data class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is Point) return false
        return this.x == other.x && this.y == other.y
    }
}