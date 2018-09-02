package szeptunm.corner.ui.schedule

import io.reactivex.subjects.PublishSubject
import szeptunm.corner.databinding.MatchItemBinding
import szeptunm.corner.ui.recycler.BindingViewHolder

class MatchViewHolder(binding: MatchItemBinding, val itemObserver: PublishSubject<Int>) :
        BindingViewHolder<MatchItem, MatchItemBinding>(binding) {

    lateinit var item: MatchItem

    override fun bind(item: MatchItem) {
        this.item = item
        binding.homeTeam.text = item.match.homeTeam.toString()
        binding.awayTeam.text = item.match.awayTeam.toString()
        binding.score.text = score()
        binding.matchDate.text = item.match.date
        binding.matchCompetition.text = item.match.competition.toString()
    }

    fun score(): String {
        val home = item.match.homeTeamGoalFull
        val away = item.match.awayTeamGoalFull
        return "$home - $away"
    }
}