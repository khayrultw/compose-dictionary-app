import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.repository.WordRepository
import com.khayrul.wordlearner.domain.util.OrderType
import com.khayrul.wordlearner.domain.util.WordOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWordsUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {
    operator fun invoke(
        wordOrder: WordOrder = WordOrder.Title(OrderType.Ascending)
    ): Flow<List<Word>> {
        return wordRepository.getWords().map { words ->
            when(wordOrder.orderType) {
                is OrderType.Ascending -> {
                    when(wordOrder) {
                        is WordOrder.Title -> words.sortedBy { it.title }
                        is WordOrder.Date -> words.sortedBy { it.date }
                    }
                }

                is OrderType.Descending -> {
                    when(wordOrder) {
                        is WordOrder.Title -> words.sortedByDescending { it.title }
                        is WordOrder.Date -> words.sortedByDescending { it.date }
                    }
                }
            }
        }
    }
}