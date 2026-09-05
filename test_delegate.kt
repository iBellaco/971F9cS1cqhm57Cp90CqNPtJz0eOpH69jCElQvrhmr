class OverlayState {
    var isExpanded: Boolean = false
}
fun test() {
    val state = OverlayState()
    var isExpanded by state::isExpanded
    isExpanded = true
}
