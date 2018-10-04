package szeptunm.corner.ui.schedule

import io.reactivex.subjects.PublishSubject
import szeptunm.corner.commons.Constants.DATE_FORMAT
import szeptunm.corner.databinding.MatchItemBinding
import szeptunm.corner.databinding.MatchNextBinding
import szeptunm.corner.ui.recycler.BindingViewHolder
import java.text.SimpleDateFormat
import java.util.Locale

class MatchPastViewHolder(binding: MatchItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<MatchItem, MatchItemBinding>(binding) {

    lateinit var item: MatchItem

    override fun bind(item: MatchItem) {
        this.item = item
        with(binding) {
            homeTeam.text = item.match.homeTeam
            awayTeam.text = item.match.awayTeam
            score.text = score()
            matchDate.text = item.match.date?.let { convertTextToDate(it) }
            matchCompetition.text = item.match.competition
        }
    }

    fun score(): String {
        val home = item.match.homeTeamGoalFull
        val away = item.match.awayTeamGoalFull
        return "$home - $away"
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