package com.loja.lojavirtual

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.firebase.auth.FirebaseAuth
import com.loja.lojavirtual.databinding.ActivityPrincipalScreenBinding
import com.loja.lojavirtual.form.FormLogin
import com.loja.lojavirtual.fragments.Product
import com.loja.lojavirtual.fragments.RegisterProducts

class PrincipalScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPrincipalScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarPrincipalScreen.toolbar)

        productsFragment()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarPrincipalScreen.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_home) {
            productsFragment()
        } else if(id == R.id.nav_add) {
            startActivity(Intent(this, RegisterProducts::class.java))
        } else if(id == R.id.nav_contact) {

        }

        val drawer = binding.drawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut()
            backFormLogin()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun backFormLogin() {
        startActivity(Intent(this, FormLogin::class.java))
    }

    private fun productsFragment() {
        val productsFragments = Product()
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.frameContainer, productsFragments)
        fragment.commit()
    }

}