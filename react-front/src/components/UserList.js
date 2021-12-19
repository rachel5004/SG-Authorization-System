import React from "react";
function User({ user }) {
  return (
    <div>
      <b>{user.name}</b> <span>{user.email}</span>
    </div>
  );
}

function UserList({ users }) {
  console.log(users);
  return (
    <div>
      {users.map((user, index) => (
        <User user={user} key={index} />
      ))}
    </div>
  );
}
export default UserList;
