package com.codepath.wishlist

import Wishlist
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var items = mutableListOf<Wishlist>()
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)
        val button = findViewById<Button>(R.id.submitButton)
        val name = findViewById<EditText>(R.id.editTextName)
        val price = findViewById<EditText>(R.id.editTextPrice)
        val url = findViewById<EditText>(R.id.editTextUrl)

        // Create adapter passing in the list of emails
        val adapter = WishlistAdapter(items)

        button.setOnClickListener {
            if (name.text.toString().isNotEmpty() && price.text.toString().isNotEmpty() && url.text.toString().isNotEmpty()) {
                val newItem = Wishlist(name.text.toString(), price.text.toString().toFloat(), url.text.toString())
                items.add(newItem)
                adapter.notifyItemInserted(count)
                count += 1
                name.text.clear()
                price.text.clear()
                url.text.clear()
            }
            else {
                Toast.makeText(it.context, "Missing Field!", Toast.LENGTH_SHORT).show()
            }

            // hide Keyboard
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        // Attach the adapter to the RecyclerView to populate items
        wishlistRv.adapter = adapter
        // Set layout manager to position the items
        wishlistRv.layoutManager = LinearLayoutManager(this)
    }
}