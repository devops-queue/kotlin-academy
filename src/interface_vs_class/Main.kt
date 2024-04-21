
import kotlin.jvm.internal.Intrinsics

interface Animal {
    val name: String
    val type: String

    val fullName: String
        get() = "$name of type $type \n"

    fun makeVoice() {
        print("<${this::class.simpleName} voice>\n")
    }
}

class Fox: Animal {
    override val type: String = "Tibetan sand fox"
    override val name: String = "Sox"
    override fun makeVoice() {
        super.makeVoice()
        print(" (I prefer to stay quiet)\n")
    }
}
class Dog: Animal {
    override val type: String = "Labardor"
    override val name: String = "Tofu"
}

fun main() {
    val fox = Fox()
    print(fox.fullName)
    fox.makeVoice() // <Animal voice>
    val dog = Dog()
    dog.makeVoice()
}


