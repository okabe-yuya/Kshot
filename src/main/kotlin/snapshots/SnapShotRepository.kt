package org.kshot.snapshots

import kotlin.io.path.Path
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists

interface SnapShotRepository {
    fun findSource(fromFileName: FileName, contentName: ContentName): SnapShot<*>?
    fun saveSource(snapShot: SnapShot<*>)
}

class StaticFileSnapShot(val currentPath: Path) : SnapShotRepository {
    private val dirName = "__tests__/__snapshots__"
    private val fileExtension = "swap"

    init {
        val dirPath = Path(dirName)
        val relativePath = currentPath.resolve(Path(dirName))
        if (!relativePath.exists()) {
            Files.createDirectories(dirPath)
        }
    }

    override fun findSource(fromFileName: FileName, contentName: ContentName): SnapShot<*>? {
        val relativePath = Path(dirName, "${fromFileName.value}.${fileExtension}")
        val snapShotFile = File(relativePath.toString())
        return if (snapShotFile.exists()) {
            return SnapShot(
                fromFileName = fromFileName,
                contentName = contentName,
                content = snapShotFile.readText(), // ファイルサイズは対して大きくならない想定なので一気に読み込ませる
            )
        } else {
            null
        }
    }

    override fun saveSource (snapShot: SnapShot<*>) {
        val relativePath = Path(dirName, "${snapShot.fromFileName.value}.${fileExtension}")
        val snapShotFile = File(relativePath.toString())

        snapShotFile.writeText(snapShot.content.prettyPrint(), Charsets.UTF_8)
    }

    private fun Any.prettyPrint(): String = prettyPrint(toString())
}