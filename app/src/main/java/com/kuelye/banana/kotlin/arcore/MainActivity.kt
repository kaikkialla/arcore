package com.kuelye.banana.kotlin.arcore

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.HitResult
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.ArFragment
import com.kuelye.banana.kotlin.arcore.model.Ratings
import com.kuelye.banana.kotlin.arcore.model.RatingsView

class MainActivity : AppCompatActivity() {

    private var arFragment: ArFragment? = null;
    private var renderable: Renderable? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        arFragment = supportFragmentManager.findFragmentById(R.id.ar_fragment) as ArFragment;
        arFragment?.setOnTapArPlaneListener { hitResult, _, _ ->
            addRatings(hitResult)
        }
    }

    private fun initialize() {
        val color = Color(0x00FF00)
        val radius = .1f
        val center = Vector3(0.0f, 0.0f, 0.0f)
        MaterialFactory.makeOpaqueWithColor(this, color) // создаём материал
            .thenAccept { material -> renderable = ShapeFactory.makeSphere(radius, center, material)}
    }

    private fun addSphere(hitResult: HitResult?) {
        val containerNode = AnchorNode(hitResult!!.createAnchor())
        containerNode.setParent(arFragment!!.arSceneView.scene)

        for (i in 0..2) {
            for (j in 0..3) {
                val node = Node()
                node.setParent(containerNode)
                node.renderable = renderable;
                node.localPosition = Vector3(i * 0.2f, 0.0f, j * 0.2f)

                val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
                animator.duration = 5000;
                animator.startDelay = (Math.random() * 1000).toLong();
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Float
                    node.localPosition = Vector3(i * 0.2f, value, j * 0.2f)
                }
                animator.start()
            }
        }
    }

    private fun addRatings(hitResult: HitResult?) {
        val containerNode = AnchorNode(hitResult!!.createAnchor())
        containerNode.setParent(arFragment!!.arSceneView.scene)

        val ratingsView = RatingsView(this)
        ratingsView.ratings = Ratings.createTestRatings();
        ViewRenderable.builder()
            .setView(this, ratingsView)
            .build()
            .thenAccept {
                val node = Node()
                node.setParent(containerNode)
                node.renderable = it
            }
    }



}
