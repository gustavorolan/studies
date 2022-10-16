import { useState } from 'react'

import { Post, Button } from '../../components'
import { BASE_POST } from '../../../constants'

import './feed.style.css'

export function FeedScreen() {
  const [posts, setPosts] = useState([])

  function handleAddPost() {
    const id = Date.now()

    const newPost = { ...BASE_POST, id , likes:[],comments:[]}

    const updatedPosts = [...posts, newPost]
    setPosts(updatedPosts)
  }

  function handleDeletePost(postId) {
    const filteredPosts = posts.filter(post => post.id !== postId)
    setPosts(filteredPosts)
  }

  function handleLikes(postId){
    const newArrayPosts=[...posts]
    newArrayPosts.find(post=>post.id===postId).likes.push('like')
    setPosts(newArrayPosts)
  }

  function handleComments(postId,comment){
    const newArrayPosts=[...posts]
    newArrayPosts.find(post=>post.id===postId).comments.push(comment.value)
    setPosts(newArrayPosts)
  }

  return (
    <main className="feed">
      <Button onClick={handleAddPost}>adicionar post</Button>
      <ul>
        {posts.map((post, key) => (
          <Post
            key={key}
            user={post.user}
            date={post.date}
            message={post.message}
            id={post.id}
            onDeletePost={handleDeletePost}
            handleLikes={handleLikes}
            likes={post.likes}
            handleComments={handleComments}
            comments={post.comments}
          />
        ))}
      </ul>
    </main>
  )
}
