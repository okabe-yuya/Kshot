package org.kshot

import org.kshot.snapshots.ContentName
import org.kshot.snapshots.FileName
import org.kshot.snapshots.SnapShot
import org.kshot.snapshots.StaticFileSnapShot
import kotlin.io.path.Path

data class A(val b: B) {
    data class B(val b: String, val a: String, val c: String)
}

fun main() {
    val snapShot = SnapShot(
        fromFileName = FileName("main"),
        contentName = ContentName("data class save"),
        content = A(b = A.B(a = "a", b = "b", c = "c")),
    )

    val snapShoter = StaticFileSnapShot(Path("./"))
    snapShoter.saveSource(snapShot)

    snapShoter.findSource(snapShot.fromFileName, snapShot.contentName)?.let {
        println(it.content)
    }
}