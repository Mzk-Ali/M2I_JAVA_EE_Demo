package fr.m2i.demo.api.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

	private JwtEncoder jwtEncoder;

	public JwtService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

	public String generateToken(String username) {

		Instant now = Instant.now();

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.DAYS))
				.subject(username).build();
		JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, claims);

		return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}

}
