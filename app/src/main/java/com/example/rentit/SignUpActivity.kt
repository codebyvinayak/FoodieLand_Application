//package com.example.rentit
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ReportFragment.Companion.reportFragment
//import com.example.rentit.model.UserModel
//import com.example.rentit.databinding.ActivitySignUpBinding
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.tasks.Task
//import com.google.firebase.Firebase
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.auth
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.database
//
//class SignUpActivity : AppCompatActivity() {
//
//    private lateinit var email: String
//    private lateinit var password: String
//    private lateinit var username: String
//    private lateinit var auth: String
//    private lateinit var database: DatabaseReference
//    private lateinit var googleSignInClint: GoogleSignClient  //add dependenc
//
//    private val binding: ActivitySignUpBinding by lazy {
//        ActivitySignUpBinding.inflate(layoutInflater)
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        val googleSignInOptions: GoogleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIDToken(getString(R.string.default_web_client_id)).requstEmail().build()
//
//        //initialize Firebase auth
//        auth = Firebase.auth
//
//        //initialize Firebase Datebase
//        database = Firebase.database.reference
//
//        //initialize Firebase Datebase
//        googleSignInClint = GoogleSignIn.getClient(this, googleSignInOptions)
//
//        binding.signupImage.setOnClickListener {
//            username = binding.editTextTextPersonEmail.text.toString()
//            email = binding.editTextPersonEmail.text.toString().trim()
//            password = binding.editTextTextPasswordName.text.toString().trim()
//
//            if (email.isEmpty() || password.isBlank() || username.isBlank()) {
//                Toast.makeText(this, "please fill all the details", Toast.LENGTH_SHORT).show()
//            } else {
//                createAccount(email, password)
//            }
//        }
//
//        binding.signupImage.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.loginRedirect.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
////        binding.bnt_facebook.setOnClickListener {
////            val signIntent: Intent = googleSignInClint.signInIntentn
////        }
//
//    }

    //launcher for google sign-in
//    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//
//            val task: Task<GoogleSignInAccount!> =
//                GoogleSignIn.getsignedInAccountFromIntent(result.data)
//            if (task.isSuccessful) {
//                val account: GoogleSignInAccount? = task.result
//                val credential: AuthCredntial =
//                    GoogleAuthProvidr.getCredential(account?.idToken, accessToken:null)
//                auth.signInwithCredential(credential).addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        startActivity(Intent(this, MainActivity::class.java))
//                        finish()
//                    } else {
//                        Toast.makeText(this, "Sing in field😔", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }else{
//                Toast.makeText(this, "Sing in field😔", Toast.LENGTH_LONG).show()
//            }
//        }


//    private fun createAccount(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
//            if(task.isSuccessfull){
//                Toast.makeText(this,"Account Created Successfully", Toast.LENGTH_LONG).show()
//                saveUserData()
//                startActivity(Intent(this,LoginActivity::class.java))
//                finish()
//            }else{
//                Toast.makeText(this,"Account Creation Failed", Toast.LENGTH_LONG).show()
//                Log.d("Account","createAccount : Failure", task.exception)
//            }
//        }
//
//    }
//
//    private fun saveUserData() {
//       // reterive data from input filled
//        username = binding.editTextTextPersonEmail.text.toString()
//        email = binding.editTextPersonEmail.text.toString().trim()
//        password = binding.editTextTextPasswordName.toString().trim()
//
//        val user = UserModel(username,email,password)
//        val userId:String = FirebaseAuth.getInstance().currentUser!!.uid
//
//        // Save Data to Firebase Database
//        database.child("user").child(userId).setValue(user)
//    }
//}

package com.example.rentit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentit.databinding.ActivitySignUpBinding
import com.example.rentit.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().reference

        binding.signupImage.setOnClickListener {
            username = binding.editTextTextPersonName.text.toString()
            email = binding.editTextPersonEmail.text.toString().trim()
            password = binding.editTextTextPasswordName.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }
        }

        binding.loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_LONG).show()
                saveUserData()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_LONG).show()
                Log.d("Account", "createAccount: Failure", task.exception)
            }
        }
    }

    private fun saveUserData() {
        val user = UserModel(username, email, password)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            database.child("user").child(it).setValue(user)
        }
    }
}


