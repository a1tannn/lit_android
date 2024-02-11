package app.ai.aitan.curry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.ai.aitan.curry.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    val people = arrayOf<String>("Android","camera","minecraft","unity")
    val food = arrayOf<String>("curry","miso soup","rice","fish and chip")

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }

    fun serveFood(view: View?){
        val index = Random().nextInt(4)
        val name = people[index]
        binding.nameTextView.text = name + "に"
        binding.foodTextView.text = food[Random().nextInt(4)] + "を"

        if(Random().nextInt(100)<50){
            binding.verbTextView.text = "よそえました^^"
        }else{
            binding.verbTextView.text = "よそえませんでした;;"
        }
    }
}