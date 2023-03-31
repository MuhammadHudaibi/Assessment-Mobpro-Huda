package org.d3if3010.assessment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import org.d3if3010.assessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var ktp = 0
        var ktpOrng = 0
        var sim = 0
        var sntk = 0


        binding.takeKtp.isEnabled == true
        binding.takeKtpAnda.isEnabled == true
        binding.takeSim.isEnabled == true
        binding.takeStnk.isEnabled == true

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        } else {
            binding.takeKtp.isEnabled == true
            binding.takeKtpAnda.isEnabled == true
            binding.takeSim.isEnabled == true
            binding.takeStnk.isEnabled == true
        }

        binding.takeKtp.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)
            ktp = 1
        }

        binding.takeKtpAnda.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 102)
            ktpOrng = 1
        }

        binding.takeSim.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 103)
            sim = 1
        }

        binding.takeStnk.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 104)
            sntk = 1
        }
        binding.submitButton.setOnClickListener { tapilanHasil() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            var pic: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            binding.fotoKtp.setImageBitmap(pic)
        } else if (requestCode == 102) {
            var pic: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            binding.fotoKtpAnda.setImageBitmap(pic)
        } else if (requestCode == 103) {
            var pic: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            binding.fotoSim.setImageBitmap(pic)
        } else if (requestCode == 104) {
            var pic: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            binding.fotoStnk.setImageBitmap(pic)

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            binding.takeKtp.isEnabled == true
            binding.takeSim.isEnabled == true
            binding.takeKtpAnda.isEnabled == true
            binding.takeStnk.isEnabled == true
        }
    }

    private fun tapilanHasil() {
        val nama = binding.namaEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val alamat = binding.alamatEditText.text.toString()
        val umur = binding.umurEditText.text.toString()
        val noHp = binding.nohpEditText.text.toString()
        val selectedDaerah = binding.radioGroup2.checkedRadioButtonId
        val selectedJenisKelamin = binding.radioGroup.checkedRadioButtonId
        val alasan = binding.alasan.text.toString()
        val setuju = binding.checkBox
        val ktp = binding.fotoKtp

        if (nama.isBlank() || email.isBlank() || alamat.isBlank() || noHp.isBlank() || selectedDaerah == -1 || selectedJenisKelamin == -1 || alasan.isBlank() || setuju.isChecked() == false) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                this,
                "Name: $nama, Email: $email, alamat: $alamat, Umur: $umur No Hp: $noHp",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

