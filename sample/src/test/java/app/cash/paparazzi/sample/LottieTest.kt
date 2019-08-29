package app.cash.paparazzi.sample

import app.cash.paparazzi.Paparazzi
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory
import org.junit.Rule
import org.junit.Test

class LottieTest {
  @get:Rule
  var paparazzi = Paparazzi()

  @Test
  fun lottie() {
    val composition = LottieCompositionFactory.fromRawResSync(paparazzi.context, R.raw.lottie_logo)
        .value!!
    val view = LottieAnimationView(paparazzi.context)
    view.setComposition(composition)
    view.progress = 1.0f

    paparazzi.snapshot(view, "lottie logo")
  }

  @Test
  fun lottie2() {
    val composition = LottieCompositionFactory.fromRawResSync(paparazzi.context, R.raw.masks)
    if (composition.value == null) {
      throw composition.exception!!
    }
    val view = LottieAnimationView(paparazzi.context)
    view.setComposition(composition.value!!)
    view.playAnimation()
//    view.progress = 0.5f

    paparazzi.snapshot(view, "masks")
  }
}
