package com.example.marvelheroes.comicdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.marvelheroes.R
import com.example.marvelheroes.api.utils.Constant
import com.example.marvelheroes.api.utils.ImageUtil
import com.example.marvelheroes.comiclist.models.Comic
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class ComicDetailsFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var comic: Comic
    private lateinit var toolbarBackground: ImageView
    private lateinit var cover: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var publishingDate: TextView
    private lateinit var price: TextView
    private lateinit var pages: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comic = requireArguments().get(Constant.COMIC) as Comic
        toolbarBackground = view.findViewById(R.id.toolbar_background)
        cover = view.findViewById(R.id.toolbar_comic_cover)
        title = view.findViewById(R.id.toolbar_comic_title)
        description = view.findViewById(R.id.comic_description)
        publishingDate = view.findViewById(R.id.publishing_date)
        price = view.findViewById(R.id.price)
        pages = view.findViewById(R.id.pages)

        initToolbar(view)

        var backgroundImage = comic.images?.firstOrNull()
        if (backgroundImage == null) {
            backgroundImage = comic.thumbnail
        }

        Picasso.get().load(
            ImageUtil.getImagePath(
                backgroundImage,
                ImageUtil.LANDSCAPE_INCREDIBLE
            )
        ).into(toolbarBackground)

        Picasso.get().load(
            ImageUtil.getImagePath(
                comic.thumbnail,
                ImageUtil.PORTRAIT_UNCANNY
            )
        ).into(cover)

        cover.setOnClickListener {
            ComicDialogFragment(
                ImageUtil.getImagePath(
                    comic.thumbnail,
                    ImageUtil.FULL_SIZE
                )
            ).show(childFragmentManager, "add_image_dialog")
        }

        title.text = comic.title
        description.text = comic.description

        val date = comic.dates?.first { d -> d.type == Constant.ON_SALE_DATE }?.date
        if (date != null) {
            publishingDate.text = SimpleDateFormat(Constant.DATE_FORMAT, Locale.US)
                .format(date)
                .toString()
        } else {
            publishingDate.text = getString(R.string.not_available)
        }

        pages.text = comic.pageCount.toString()

        val priceAux = comic.prices?.first { p -> p.type == Constant.PRINT_PRICE }?.price
        if (priceAux != null) {
            price.text = getString(R.string.price_format, priceAux)
        } else {
            price.text = getString(R.string.not_available)
        }
    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        val activity = activity as AppCompatActivity

        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayShowTitleEnabled(false)

        navController = findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)

        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration)
    }
}