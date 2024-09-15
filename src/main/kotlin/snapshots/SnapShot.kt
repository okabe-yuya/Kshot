package org.kshot.snapshots

import java.nio.file.Path

data class ContentName(val value: String)
data class FileName(val value: String)

class SnapShot<T: Any> (
    val fromFileName: FileName,
    val contentName: ContentName,
    val content: T,
)
