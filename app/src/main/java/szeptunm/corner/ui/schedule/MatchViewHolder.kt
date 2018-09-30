package szeptunm.corner.ui.schedule

import io.reactivex.subjects.PublishSubject
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import szeptunm.corner.databinding.MatchItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class MatchViewHolder(binding: MatchItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<MatchItem, MatchItemBinding>(binding) {

    lateinit var item: MatchItem

    override fun bind(item: MatchItem) {
        this.item = item
        binding.homeTeam.text = item.match.homeTeam
        binding.awayTeam.text = item.match.awayTeam
        binding.score.text = score()
//        binding.matchDate.text = item.match.date?.let { convertText(it) }
        binding.matchCompetition.text = item.match.competition
    }

    fun score(): String {
        val home = item.match.homeTeamGoalFull
        val away = item.match.awayTeamGoalFull
        return "$home - $away"
    }

    private fun convertText(date: String): String {
        val destinationFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")
        return destinationFormatter.print(LocalDate(date))
    }
}