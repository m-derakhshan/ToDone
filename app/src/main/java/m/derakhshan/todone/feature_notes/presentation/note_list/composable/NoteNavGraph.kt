package m.derakhshan.todone.feature_notes.presentation.note_list.composable

sealed class NoteNavGraph(val route: String) {
    object NoteListScreen : NoteNavGraph("NoteListScreen")
    object NoteScreen: NoteNavGraph("NoteScreen")
}
