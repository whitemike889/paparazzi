//import android.widget.LinearLayout
//import com.squareup.cash.screenshot.R
//import com.squareup.cash.screenshot.jvm.Screenshotter
//import com.android.ide.common.rendering.api.SessionParams.RenderingMode
//import android.os.Handler_Delegate.setCallback
//import android.os.Handler_Delegate.setCallback
//import com.android.layoutlib.bridge.intensive.setup.LayoutLibTestCallback
//import com.android.layoutlib.bridge.intensive.setup.LayoutPullParser
//import android.annotation.NonNull
//import android.content.res.AssetManager
//import android.content.res.ColorStateList
//import android.content.res.Configuration
//import android.content.res.Resources
//import android.content.res.Resources_Delegate
//import android.graphics.Color
//import android.util.DisplayMetrics
//import android.util.StateSet
//import android.util.TypedValue
//import android.widget.LinearLayout
//import com.android.ide.common.rendering.api.RenderSession
//import com.android.ide.common.rendering.api.ResourceNamespace
//import com.android.ide.common.rendering.api.ResourceValue
//import com.android.ide.common.rendering.api.SessionParams
//import com.android.ide.common.rendering.api.SessionParams.RenderingMode
//import com.android.ide.common.rendering.api.ViewInfo
//import com.android.internal.R
//import com.android.layoutlib.bridge.android.BridgeContext
//import com.android.layoutlib.bridge.android.RenderParamsFlags
//import com.android.layoutlib.bridge.impl.ParserFactory
//import com.android.layoutlib.bridge.impl.RenderAction
//import com.android.layoutlib.bridge.impl.RenderActionTestUtil
//import com.android.layoutlib.bridge.impl.ResourceHelper
//import com.android.layoutlib.bridge.intensive.setup.ConfigGenerator
//import com.android.layoutlib.bridge.intensive.setup.LayoutLibTestCallback
//import com.android.layoutlib.bridge.intensive.setup.LayoutPullParser
//import com.android.layoutlib.bridge.intensive.util.ModuleClassLoader
//import com.android.layoutlib.bridge.intensive.util.SessionParamsBuilder
//import com.android.resources.Density
//import com.android.resources.Navigation
//import com.android.resources.ResourceType
//import com.android.resources.ResourceUrl
//import com.google.android.collect.Lists
//import com.squareup.cash.screenshot.R
//import org.junit.*
//import org.kxml2.io.KXmlParser
//import org.xmlpull.v1.XmlPullParser
//import org.xmlpull.v1.XmlPullParserException
//
//import java.awt.BasicStroke
//import java.awt.Graphics2D
//import java.awt.image.BufferedImage
//import java.io.File
//import java.io.FileNotFoundException
//import java.io.FileOutputStream
//import java.io.PrintWriter
//import java.lang.reflect.Field
//import java.util.concurrent.TimeUnit
//
//import org.junit.Assert.assertArrayEquals
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertFalse
//import org.junit.Assert.assertNotEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Assert.assertTrue
//
//
//class BoostViewTest {
//    private val PLATFORM_DIR_PROPERTY = "platform.dir"
//    private val RESOURCE_DIR_PROPERTY = "test_res.dir"
//
//    val PLATFORM_DIR: String? = null
//    private val TEST_RES_DIR: String? = null
//    /** Location of the app to test inside [.TEST_RES_DIR]  */
//    val APP_TEST_DIR = "testApp/MyApplication"
//    /** Location of the app's res dir inside [.TEST_RES_DIR]  */
//    private val APP_TEST_RES = "$APP_TEST_DIR/src/main/res"
//    /** Location of the app's asset dir inside [.TEST_RES_DIR]  */
//    private val APP_TEST_ASSET = "$APP_TEST_DIR/src/main/assets/"
//    private val APP_CLASSES_LOCATION = "$APP_TEST_DIR/build/intermediates/classes/debug/"
////    var sBridge: Bridge? = null
//    /** List of log messages generated by a render call. It can be used to find specific errors  */
//    var sRenderMessages = mutableListOf<String>()
//
//    protected var mDefaultClassLoader: ClassLoader? = null
//
//    @field:Rule
//    val snapshotter = Screenshotter()
//
//    @Before
//    fun beforeTestCase() {
//        // Default class loader with access to the app classes
//        mDefaultClassLoader = ModuleClassLoader(APP_CLASSES_LOCATION, BoostViewTest::class.java.getClassLoader())
//        sRenderMessages.clear()
//    }
//
//    @Test
//    fun idle() {
//        val boostView: LinearLayout = snapshotter.inflate(com.squareup.cash.screenshot.R.layout.boost_view)
//        snapshotter.snapshot(boostView)
//    }
//
//    @Test
//    fun vector_drawable() {
//        // Create the layout pull parser.
//        val parser = LayoutPullParser.createFromString(
//            "<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
//            "              android:padding=\"16dp\"\n" +
//            "              android:orientation=\"horizontal\"\n" +
//            "              android:layout_width=\"fill_parent\"\n" +
//            "              android:layout_height=\"fill_parent\">\n" +
//            "    <ImageView\n" +
//            "             android:layout_height=\"fill_parent\"\n" +
//            "             android:layout_width=\"fill_parent\"\n" +
//            "             android:src=\"@drawable/multi_path\" />\n" + "\n" +
//            "</LinearLayout>"
//        )
//        // Create LayoutLibCallback.
//        val layoutLibCallback = LayoutLibTestCallback(getLogger(), mDefaultClassLoader)
//        layoutLibCallback.initResources()
//
//        val params = getSessionParamsBuilder()
//.setParser(parser)
//.setCallback(layoutLibCallback)
//.setTheme("Theme.Material.NoActionBar.Fullscreen", false)
//.setRenderingMode(RenderingMode.V_SCROLL)
//.build()
//
//        renderAndVerify(params, "vector_drawable.png", TimeUnit.SECONDS.toNanos(2))
//    }
//
//    protected SessionParamsBuilder getSessionParamsBuilder() {
//        return SessionParamsBuilder()
//                .setLayoutLog(getLayoutLog())
//                .setFrameworkResources(sFrameworkRepo)
//                .setConfigGenerator(ConfigGenerator.NEXUS_5)
//                .setProjectResources(sProjectResources)
//                .setTheme("AppTheme", true)
//                .setRenderingMode(RenderingMode.NORMAL)
//                .setTargetSdk(22)
//                .setFlag(RenderParamsFlags.FLAG_DO_NOT_RENDER_ON_CREATE, true)
//                .setAssetRepository(new TestAssetRepository(TEST_RES_DIR + "/" + APP_TEST_ASSET));
//    }
//
//    fun getLayoutLog() : LayoutLog {
//        if (sLayoutLibLog == null) {
//            sLayoutLibLog = new LayoutLog() {
//                @Override
//                public void warning(String tag, String message, Object data) {
//                    System.out.println("Warning " + tag + ": " + message);
//                    failWithMsg(message);
//                }
//
//                @Override
//                public void fidelityWarning(String tag, String message, Throwable throwable,
//                        Object viewCookie, Object data) {
//                    System.out.println("FidelityWarning " + tag + ": " + message);
//                    if (throwable != null) {
//                        throwable.printStackTrace();
//                    }
//                    failWithMsg(message == null ? "" : message);
//                }
//
//                @Override
//                public void error(String tag, String message, Object data) {
//                    System.out.println("Error " + tag + ": " + message);
//                    failWithMsg(message);
//                }
//
//                @Override
//                public void error(String tag, String message, Throwable throwable, Object data) {
//                    System.out.println("Error " + tag + ": " + message);
//                    if (throwable != null) {
//                        throwable.printStackTrace();
//                    }
//                    failWithMsg(message);
//                }
//            };
//        }
//        return sLayoutLibLog;
//    }
//
//}