import org.mockito.Mockito

// Workaround for Mockito.
// More details here https://discuss.kotlinlang.org/t/how-to-use-mockito-with-kotlin/324
fun <T> anyObject(): T {
    return Mockito.any<T>()
}
