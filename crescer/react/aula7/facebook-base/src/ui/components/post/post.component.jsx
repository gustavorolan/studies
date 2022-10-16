import { Button } from '../button/button.component'

import './post.style.css'

export function Post({ user, date, message, id, onDeletePost,handleLikes,likes,handleComments,comments}) {
  const inputComment = document.querySelector(`.inputComment${id}`)
  function handleDeletePost() {
    onDeletePost(id)
  }
 function onComments(){
   if(!!inputComment) handleComments(id,inputComment)
 }
console.log(comments)
  return (
    <li className="post">
      <header className="post__header">
        <img
          className="post__user-image"
          src={user.image}
          alt={`Foto do ${user.name}.`}
        />
        <div className="post__user-info">
          <h2 className="post__user-name">{user.name}</h2>
          <h3 className="post__date">{date}</h3>
          <h4 className="post__id">{id}</h4>
        </div>
      </header>

      <p className="post__message">{message}</p>

      <div className="post__actions">
        <Button onClick={handleDeletePost}>deletar post</Button>
        <Button onClick={()=>handleLikes(id)}>{likes.length} likes</Button>
          <input type="text" className={`inputComment${id}`}></input>
          <Button onClick={onComments}> Comment</Button>
          
        <div className='comments'>
          {comments.map((comment)=> <p>{comment}</p>)
          }
        </div>
      </div>
    </li>
  )
}
