import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import FavoriteSites from './components/FavoriteSites.tsx';
import LoginForm from "./components/forms/LoginForm.tsx";
import RegistrationForm from "./components/forms/RegistrationForm.tsx";
import Home from './components/Home.tsx';
function App() {
	

	return (
		// <div className="App">
		//     {getUsers()}
		// </div>
		<>
		
		<Router>

			<Routes>
			
				<Route path="/login" element={<LoginForm />} />
				<Route path="/register" element={<RegistrationForm />} />
				<Route path='/home' element={<Home />} />
				<Route path='/favorites' element={<FavoriteSites />} />

			</Routes>
			
		</Router>
		</>
	);
}

export default App;
