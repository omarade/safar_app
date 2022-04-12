import UserService from "./services/UserService";

function App() {

  function getUsers(){
    UserService.getUsers()
    // .then( (res) => {
    //   let users = res.data
    //   return users;
    // })
  }
  

  return (
    <div className="App">
        {getUsers()}
    </div>
  );
}

export default App;
