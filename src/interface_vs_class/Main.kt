interface Animal {
    fun makeVoice() {
        print("<Animal voice>")
    }
}

class Fox: Animal

fun main() {
    val fox = Fox()
    fox.makeVoice() // <Animal voice>
}
