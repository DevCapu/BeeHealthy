package br.com.devcapu.beehealthy.local

import android.content.Context
import java.io.IOException
import java.io.InputStream

class JsonUtil {
    companion object {
        fun getJsonFromAssets(context: Context, fileName: String): String? {
            return try {
                val inputStream: InputStream = context.assets.open(fileName)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer)
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }
    }
}