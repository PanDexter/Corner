package szeptunm.corner.ui.team

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.commons.Constants.KEY_CLUB_NAME
import szeptunm.corner.commons.Preferences
import szeptunm.corner.databinding.PlayerItemBinding
import szeptunm.corner.domain.splashScreen.GetClubInfoByName
import szeptunm.corner.ui.recycler.BindingViewHolder
import javax.inject.Inject

class PlayerViewHolder @Inject constructor(binding: PlayerItemBinding, val itemObserver: PublishSubject<Int>,
        val fragment: TeamFragment, val getClubInfoByName: GetClubInfoByName) :
        BindingViewHolder<PlayerItem, PlayerItemBinding>(binding) {

    companion object {
        const val KEY_PLAYER = "KEY_PLAYER"
    }

    private val compositeDisposable = CompositeDisposable()
    lateinit var item: PlayerItem

    private val sharedPreferences = context.getSharedPreferences(Preferences.PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE)!!

    private val clubName: String
        get() = sharedPreferences.getString(KEY_CLUB_NAME, "")

    override fun bind(item: PlayerItem) {
        this.item = item

        binding.containerPlayer.setOnClickListener {
            prepareTransition()
        }
        val requestOptions = RequestOptions()
        getClubInfoByName.execute(clubName)
                .subscribeOn(Schedulers.computation())
                .subscribe { club ->
                    val clubBadge = context.resources.getDrawable(club.badge)
                    requestOptions
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontTransform()
                            .priority(LOW)
                            .placeholder(clubBadge)
                }.addTo(compositeDisposable)
        Glide.with(itemView).load(getImage()).apply(requestOptions).into(binding.playerPhoto)
        binding.playerName.text = item.player.name
        binding.playerPosition.text = item.player.position
    }

    private fun getImage(): String? {
        return if (item.player.cutOutUrl == null) item.player.thumbUrl else item.player.cutOutUrl
    }

    private fun prepareTransition() {
        val intent = Intent(context, PlayerDetailActivity::class.java)
        intent.putExtra(KEY_PLAYER, item.player)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.requireActivity(),
                binding.playerPhoto, "playerPhoto")
        context.startActivity(intent, options.toBundle())
    }
}