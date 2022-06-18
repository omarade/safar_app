import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import LoginForm from "./components/forms/LoginForm.tsx";
import RegistrationForm from "./components/forms/RegistrationForm.tsx";
import SiteCard from './components/home/SiteCard.tsx';
function App() {
	

	return (
		// <div className="App">
		//     {getUsers()}
		// </div>
		<Router>

			<Routes>
			
				<Route path="/login" element={<LoginForm />} />
				<Route path="/register" element={<RegistrationForm />} />
				<Route path='/home' element={<SiteCard />} />
			
			</Routes>
			
		</Router>
		
	);
}

export default App;
