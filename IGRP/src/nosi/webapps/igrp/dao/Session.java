package nosi.webapps.igrp.dao;

/**
 * @author: Emanuel Pereira
 * 29 Jun 2017
 */
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import org.apache.commons.lang.time.DateUtils;

import nosi.base.ActiveRecord.BaseActiveRecord;
import nosi.core.config.Connection;
import nosi.core.gui.components.IGRPBox;
import nosi.core.webapp.FlashMessage;
import nosi.core.webapp.Igrp;

@Entity
@Table(name = "tbl_session")
public class Session extends BaseActiveRecord<Session> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5383008060171825399L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String sessionId;
	// ==============Antes==============
	// private Long startTime;
	// private Long endTime;
	// ==============Agora==============
	private Timestamp startTime;
	private Timestamp endTime;
	// =================================
	private String ipAddress;
	private String userName;
	private String target;
	private int https; // boolean 0 or 1
	private String sessionOldId;
	private String host;
	private String hostName;
	private String mediaType;

	@ManyToOne
	@JoinColumn(name = "user_fk", foreignKey = @ForeignKey(name = "SESSION_USER_FK"))
	private User user;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "env_fk", foreignKey = @ForeignKey(name = "SESSION_ENV_FK"), nullable = false)
	private Application application;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "prof_type_fk", foreignKey = @ForeignKey(name = "SESSION_PROF_TYPE_FK"), nullable = false)
	private ProfileType profileType;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "org_fk", foreignKey = @ForeignKey(name = "SESSION_ORGANIZATION_FK"), nullable = true)
	private Organization organization;

	public Session() {
	}

	public Session(String sessionId, Timestamp startTime, Timestamp endTime, String ipAddress, String userName,
			String target, int https, String sessionOldId, String host, String hostName, String mediaType, User user,
			Application application, ProfileType profileType, Organization organization) {
		super();
		this.sessionId = sessionId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ipAddress = ipAddress;
		this.userName = userName;
		this.target = target;
		this.https = https;
		this.sessionOldId = sessionOldId;
		this.host = host;
		this.hostName = hostName;
		this.mediaType = mediaType;
		this.user = user;
		this.application = application;
		this.profileType = profileType;
		this.organization = organization;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getHttps() {
		return https;
	}

	public void setHttps(int https) {
		this.https = https;
	}

	public String getSessionOldId() {
		return sessionOldId;
	}

	public void setSessionOldId(String sessionOldId) {
		this.sessionOldId = sessionOldId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public static boolean afterLogin(Profile profile) {
		Session currentSession = new Session();
		currentSession.setUser(new User().findOne(Igrp.getInstance().getUser().getIdentity().getIdentityId()));
		User user = ((User) Igrp.getInstance().getUser().getIdentity());
		currentSession.setApplication(new Application().findOne(user.getAplicacao().getId()));
		currentSession.setOrganization(profile.getOrganization());
		currentSession.setProfileType(profile.getProfileType());
		currentSession.setIpAddress(Igrp.getInstance().getRequest().getRemoteAddr());
		currentSession.setSessionId(Igrp.getInstance().getRequest().getRequestedSessionId());
		currentSession.setUserName(user.getUser_name());
		currentSession.setHttps(Igrp.getInstance().getRequest().isSecure() ? 1 : 0);
		currentSession.setHost(Igrp.getInstance().getRequest().getRemoteHost());
		currentSession.setHostName(Igrp.getInstance().getRequest().getRemoteHost());
		currentSession.setSessionOldId(Igrp.getInstance().getRequest().getRequestedSessionId());
		currentSession.setMediaType("WEB");
		currentSession.setTarget("_blank");
		Timestamp time = new Timestamp(System.currentTimeMillis());// System.currentTimeMillis();
		currentSession.setStartTime(time);

		// Esta parte vai ter de estar definido
		currentSession.setEndTime(time); // add 30 min.
		currentSession = currentSession.insert();
		return currentSession != null;
	}

	public static boolean afterLogout(String currentSessionId) {
		Session session = new Session().find().andWhere("sessionId", "=", currentSessionId).one();
		if (session != null) {
			session.setEndTime(new Timestamp(System.currentTimeMillis()));
		}
		return session != null && session.getApplication() != null && session.update() != null;
	}

	public class FetchForChart {
		// =================================
		private String start;
		private int total;
		private String appName;
		private String startPerApp;
		private int totalPerApp;

		public String getStart() {
			return start;
		}

		public void setStart(String start) {
			this.start = start;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public String getAppName() {
			return appName;
		}

		public void setAppName(String appName) {
			this.appName = appName;
		}

		public String getStartPerApp() {
			return startPerApp;
		}

		public void setStartPerApp(String startPerApp) {
			this.startPerApp = startPerApp;
		}

		public int getTotalPerApp() {
			return totalPerApp;
		}

		public void setTotalPerApp(int totalPerApp) {
			this.totalPerApp = totalPerApp;
		}
		// ========================================================================
		

		public String totalSessionSql() throws IllegalArgumentException, IllegalAccessException {
			nosi.webapps.igrp.pages.session.Session sessionModel = new nosi.webapps.igrp.pages.session.Session();
			Session session = new Session();
			// =================================================================
			if (Igrp.getInstance().getRequest().getMethod().equals("POST")) {
				sessionModel.load();
			}

			// =================================================================
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtualFinalAux = DateUtils.addDays(new Date(), 1);
			Date dataAtualInicialAux = DateUtils.addDays(new Date(), -3);
			String dataActualFinal = formatDate.format(dataAtualFinalAux);
			String dataActualInicial = formatDate.format(dataAtualInicialAux);
			// =================================================================
System.out.println("*********PPPPPPPPPPPPPPPPP***********"+ sessionModel.getEstado());
			// Authenticaded User
			// =================================================================
			User authenticatedUser = (User) Igrp.getInstance().getUser().getIdentity();
			// =================================================================
			String dateIGeted = sessionModel.getData_inicio();
			String dateFGeted = sessionModel.getData_fim();
			// =================================================================
			String dataInicio = (dateIGeted == null || dateIGeted.equals("")) ? dataActualInicial : dateIGeted;
			String dataFim = (dateFGeted == null || dateFGeted.equals("")) ? dataActualFinal : dateFGeted;
			String username = sessionModel.getUtilizador() == null ? authenticatedUser.getUser_name()
					: sessionModel.getUtilizador();
			int status = sessionModel.getEstado() == 0? authenticatedUser.getStatus():sessionModel.getEstado();
			int app = (sessionModel.getAplicacao() == 0) ? 1 : sessionModel.getAplicacao();

			if (dataInicio != dataActualInicial) {
				dataInicio = session.convertDate(dateIGeted, "dd-MM-yyyy", "yyyy-MM-dd");
			}
			if (dataFim != dataActualFinal) {
				dataFim = session.convertDate(dateFGeted, "dd-MM-yyyy", "yyyy-MM-dd");
			}

			if (dataInicio.compareTo(dataFim) > 0 || dataInicio.equals(dataFim)) {
				System.out.println("Esta filtração nao e valida");
				// ===================================================================
				Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.WARNING,
						"Certifique-se de introduzir intervalo válido");
			}

			String sqlTotal = "SELECT CONVERT(s.starttime, DATE) as datainicio, s.username, COUNT(*) as Total "
					+ "FROM TBL_SESSION s " + "INNER JOIN tbl_user t on t.id = s.user_fk " + "where t.status = '"
					+ status + "' and t.user_name like '%" + username + "%' " + "and (s.starttime BETWEEN '"
					+ dataInicio + "' AND '" + dataFim + "') " + "AND s.env_fk = '" + app + "' "
					+ "GROUP BY CONVERT(s.starttime, DATE) " + "ORDER BY CONVERT(s.starttime, DATE) DESC";
			return sqlTotal;
		}

		public String totalSessionPerAppSql() throws IllegalArgumentException, IllegalAccessException {
			nosi.webapps.igrp.pages.session.Session sessionModel = new nosi.webapps.igrp.pages.session.Session();
			Session session = new Session();
			
			// =================================================================
			if (Igrp.getInstance().getRequest().getMethod().equals("POST")) {
				sessionModel.load();
			}
			// =================================================================
			
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtualFinalAux = DateUtils.addDays(new Date(), 1);
			Date dataAtualInicialAux = DateUtils.addDays(new Date(), -3);
			String dataActualFinal = formatDate.format(dataAtualFinalAux);
			String dataActualInicial = formatDate.format(dataAtualInicialAux);
			// =================================================================

			// Authenticaded User
			// =================================================================
			User authenticatedUser = (User) Igrp.getInstance().getUser().getIdentity();
			// =================================================================
			String dateIGeted = sessionModel.getData_inicio();
			String dateFGeted = sessionModel.getData_fim();
			// =================================================================
			String dataInicio = (dateIGeted == null || dateIGeted.equals("")) ? dataActualInicial : dateIGeted;
			String dataFim = (dateFGeted == null || dateFGeted.equals("")) ? dataActualFinal : dateFGeted;
			String username = sessionModel.getUtilizador() == null ? authenticatedUser.getUser_name()
					: sessionModel.getUtilizador();
			int status = sessionModel.getEstado() == 0? authenticatedUser.getStatus():sessionModel.getEstado();
			int app = (sessionModel.getAplicacao() == 0) ? 1 : sessionModel.getAplicacao();

			if (dataInicio != dataActualInicial) {
				dataInicio = session.convertDate(dateIGeted, "dd-MM-yyyy", "yyyy-MM-dd");
			}
			if (dataFim != dataActualFinal) {
				dataFim = session.convertDate(dateFGeted, "dd-MM-yyyy", "yyyy-MM-dd");
			}

			if (dataInicio.compareTo(dataFim) > 0 || dataInicio.equals(dataFim)) {
				System.out.println("Esta filtração nao e valida");
				// ===================================================================
				Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.WARNING,
						"Certifique-se de introduzir intervalo válido");
			}
			
			
			String sqlTopalPerApp= "SELECT CONCAT( a.dad, ' - ', a.name ) as appname, "
			+"CONVERT(s.starttime, DATE) as datainicio, COUNT(*) as total "
			+"FROM TBL_ENV a, TBL_SESSION s "
			+"INNER JOIN tbl_user t on t.id = s.user_fk "
			+"where t.status = '"+status+"' and t.user_name like '%"+username+"%'  "
			+"AND a.id = s.env_fk AND s.env_fk = '"+app+"' AND (CONVERT(s.starttime, DATE) "
			+"BETWEEN '"+dataInicio+"' AND '"+dataFim+"')  "
			+"GROUP BY CONCAT( a.dad, ' - ', a.name ), CONVERT(s.starttime, DATE) "
			+"ORDER BY CONVERT(s.starttime, DATE) DESC, a.dad DESC";


			return sqlTopalPerApp;
		}
	}

	public List<nosi.webapps.igrp.dao.Session.FetchForChart> fechTotalSession() throws SQLException, IllegalArgumentException, IllegalAccessException {
		List<nosi.webapps.igrp.dao.Session.FetchForChart> result = new ArrayList<>();
		// ======================================Query=============================================
		Session.FetchForChart session = new Session.FetchForChart();
		// ========================================================================================

		PreparedStatement statement = Connection.getConnection().prepareStatement(session.totalSessionSql());
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			Session.FetchForChart s = new Session.FetchForChart();
			s.setStart(res.getString("datainicio"));
			s.setTotal(res.getInt("total"));
			result.add(s);
		}
		return result;
	}

	public List<nosi.webapps.igrp.dao.Session.FetchForChart> fechTotalSessionPerApp() throws SQLException, IllegalArgumentException, IllegalAccessException {
		List<nosi.webapps.igrp.dao.Session.FetchForChart> result = new ArrayList<>();
		// ======================================Query=============================================
		Session.FetchForChart session = new Session.FetchForChart();
		// ========================================================================================

		PreparedStatement statement = Connection.getConnection().prepareStatement(session.totalSessionPerAppSql());
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			Session.FetchForChart s = new Session.FetchForChart();
			s.setStartPerApp(res.getString("datainicio"));
			// s.setAppName(res.getString("appname"));
			s.setTotalPerApp(res.getInt("total"));
			result.add(s);
		}
		return result;
	}


	// ===================================================
	public String convertDate(String date, String formatIn, String formatOut) {
		String myDateString = null;
		try {
			SimpleDateFormat newDateFormat = new SimpleDateFormat(formatIn);
			Date myDate = newDateFormat.parse(date);
			newDateFormat.applyPattern(formatOut);
			myDateString = newDateFormat.format(myDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myDateString;
	}

	// ===================================================

	// public ResultSet fetchDataPerSql(String sql) throws SQLException {
	// PreparedStatement statement =
	// Connection.getConnection().prepareStatement(sql); // Execução de query
	// ResultSet res = statement.executeQuery();
	// return res;
	// }

	// String sql = "SELECT * FROM GLB_T_SESSION where 1=1 ";
	// sql += this.envId != 0 ? " and env_id = " + this.envId : "";
	// sql += this.userName != null && !this.userName.equals("") ? " and user_name =
	// '" + this.userName + "' " : "";
	// //sql += this.startTime != 0 ? " and (start_time - " + this.startTime + " <
	// 24*60)" : "";
	// //sql += this.endTime != 0 ? " and (end_time - " + this.endTime + " < 24*60)"
	// : "";
	// String sql = "select a.start_time data, count(*) total from glb_t_session a
	// where a.start_time between ? and ? group by a.start_time order by 1;";
}