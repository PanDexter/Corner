package szeptunm.corner.ui.schedule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority.LOW
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.subjects.PublishSubject
import szeptunm.corner.R
import szeptunm.corner.commons.Constants.DATE_FORMAT
import szeptunm.corner.commons.Constants.KEY_CLUB_ID
import szeptunm.corner.commons.Preferences
import szeptunm.corner.databinding.MatchItemBinding
import szeptunm.corner.databinding.MatchNextBinding
import szeptunm.corner.ui.recycler.BindingViewHolder
import java.text.SimpleDateFormat
import java.util.Locale

class MatchPastViewHolder(binding: MatchItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<MatchItem, MatchItemBinding>(binding) {

    companion object {
        const val KEY_MATCH = "KEY_MATCH"
    }

    lateinit var item: MatchItem

    private val sharedPreferences = context.getSharedPreferences(Preferences.PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE)!!

    private val clubId: Int
        get() = sharedPreferences.getInt(KEY_CLUB_ID, 0)

    override fun bind(item: MatchItem) {
        this.item = item
        with(binding) {
            scoreboardItem.setOnClickListener {
                val intent = Intent(context, MatchDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(KEY_MATCH, item.match)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
            homeTeam.text = item.match.homeTeam
            awayTeam.text = item.match.awayTeam
            score.text = score()
            matchDate.text = item.match.date?.let { convertTextToDate(it) }
            matchCompetition.text = item.match.competition
            setupIcon(item)
        }
    }

    fun score(): String {
        val home = item.match.homeTeamGoalFull
        val away = item.match.awayTeamGoalFull
        return "$home - $away"
    }

    private fun setupIcon(item: MatchItem) {
        val homeScore: Int = item.match.homeTeamGoalFull!!
        val awayScore: Int = item.match.awayTeamGoalFull!!
        val requestOptions = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .priority(LOW)
        if (item.match.homeTeamId == clubId) {
            when {
                homeScore > awayScore -> Glide.with(itemView).load(
                        ContextCompat.getDrawable(context, R.drawable.win)).apply(requestOptions).into(
                        binding.teamScore)
                homeScore < awayScore -> Glide.with(itemView).load(
                        ContextCompat.getDrawable(context, R.drawable.lost)).apply(requestOptions).into(
                        binding.teamScore)
            }
        } else {
            when {
                homeScore > awayScore -> Glide.with(itemView).load(
                        ContextCompat.getDrawable(context, R.drawable.lost)).apply(requestOptions).into(
                        binding.teamScore)
                homeScore < awayScore -> Glide.with(itemView).load(
                        ContextCompat.getDrawable(context, R.drawable.win)).apply(requestOptions).into(
                        binding.teamScore)
            }
        }
        if (homeScore == awayScore) {
            Glide.with(itemView).load(ContextCompat.getDrawable(context, R.drawable.draw)).apply(
                    requestOptions).into(binding.teamScore)
        }
    }

    private fun convertTextToDate(date: String): String =
            SimpleDateFormat("dd/MM", Locale.getDefault()).format(DATE_FORMAT.parse(date))
}

class MatchFutureViewHolder(binding: MatchNextBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<MatchItem, MatchNextBinding>(binding) {

    lateinit var item: MatchItem

    override fun bind(item: MatchItem) {
        this.item = item
        with(binding) {
            homeTeam.text = item.match.homeTeam
            awayTeam.text = item.match.awayTeam
            matchCompetition.text = item.match.competition
            matchDate.text = item.match.date?.let { convertTextToDate(it) }
            matchHour.text = item.match.date?.let { convertTextToHour(it) }
        }
    }

    private fun convertTextToDate(date: String): String =
            SimpleDateFormat("dd.MM", Locale.getDefault()).format(DATE_FORMAT.parse(date))

    private fun convertTextToHour(date: String): String {
        val formatted = SimpleDateFormat("HH:mm", Locale.getDefault()).format(DATE_FORMAT.parse(date))
        return if (formatted != "00:00") formatted else ""
    }
}